import os

from filter import Filter
from metrics import calculate_metrics
from utils import export_json, calculate_all_jaccard_similarity
from visiulize import visualize_projects_jaccard, visualize_exclusive_package_line_of_codes, visualize_matrix, \
    visualize_metrics


class Pipeline:
    def __init__(self) -> None:
        self.filters = []
        self.filter = Filter()
        self.projects = []

    def execute(self, data_path, source_path):
        self.projects = self.filter.extract_and_load_metadata(data_path)

        jaccards_similarity_projects = calculate_all_jaccard_similarity(
            self.projects)

        packages_frequency = self.filter.calculate_packages_frequency(
            self.projects)

        internal_package_calls = self.filter.extract_internal_package_calls(
            self.projects)

        projects_map = [project.get_dict() for project in self.projects]

        # export features of projects
        export_json(jaccards_similarity_projects, "jaccards", source_path=source_path)
        export_json(projects_map, "semantics", source_path=source_path)
        export_json(internal_package_calls, "internal_package_calls", source_path=source_path)

        visualize_exclusive_package_line_of_codes(self.projects)
        visualize_projects_jaccard(self.projects)

        thresholds = [0.0, 0.3, 0.8]
        thresholds_name = ["threshold" + str(threshold) for threshold in thresholds]
        metrics_results = []
        for i, threshold in enumerate(thresholds):
            if not os.path.exists("outputs/"):
                os.mkdir("outputs/")

            if not os.path.exists("outputs/" + thresholds_name[i]):
                os.mkdir("outputs/" + thresholds_name[i])
            remain_packages = self.filter.extract_remain_packages_by_threshold(
                packages_frequency, threshold)

            print(
                f"With Threshold {threshold} ,{len(remain_packages)} remain packages is {remain_packages}")

            updated_projects = self.filter.updated_projects_remove_outlier_package(remain_packages)

            metrics_results.append(calculate_metrics(threshold, updated_projects, internal_package_calls))
            visualize_matrix(updated_projects, internal_package_calls, thresholds_name[i])

        visualize_metrics(metrics_results, "")
