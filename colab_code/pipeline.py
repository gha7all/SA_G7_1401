import os

from export_pdf import export_pdf
from filter import Filter
from metrics import calculate_metrics
from utils import export_json, calculate_all_jaccard_similarity
from visiulize import visualize_projects_jaccard, visualize_exclusive_package_line_of_codes, visualize_matrix, \
    visualize_metrics
from lattis import draw_lattis


class Pipeline:
    def __init__(self) -> None:
        self.filter = Filter()
        self.projects = []

        if not os.path.exists("outputs/"):
            os.mkdir("outputs/")

    def execute(self, data_path, source_path):

        self.projects = self.filter.extract_and_load_metadata(data_path)

        jaccards_similarity_projects = calculate_all_jaccard_similarity(
            self.projects)

        packages_frequency = self.filter.calculate_packages_frequency(
            self.projects)

        internal_package_calls = self.filter.extract_internal_package_calls(
            self.projects)

        draw_lattis(internal_package_calls, self.projects, "outputs/lattis")

        projects_map = [project.get_dict() for project in self.projects]

        # export features of projects
        export_json(jaccards_similarity_projects, "outputs/jaccards", source_path=source_path)
        export_json(projects_map, "outputs/semantics", source_path=source_path)
        export_json(internal_package_calls, "outputs/internal_package_calls", source_path=source_path)

        thresholds = [0.0, 0.2, 0.5]
        thresholds_name = ["threshold" + str(threshold) for threshold in thresholds]
        metrics_results = []
        meta_data_of_results = []
        for i, threshold in enumerate(thresholds):
            color = ['tab:purple', 'tab:red', 'tab:green', 'tab:orange', 'tab:olive'][i % 5]

            if not os.path.exists("outputs/" + thresholds_name[i]):
                os.mkdir("outputs/" + thresholds_name[i])
            remain_packages = self.filter.extract_remain_packages_by_threshold(
                packages_frequency, threshold)

            des_remain_projects = ", ".join(remain_packages)
            des = f"With Threshold {threshold}, {len(remain_packages)} remain packages is {des_remain_projects} ."

            updated_projects = self.filter.updated_projects_remove_outlier_package(remain_packages)

            metrics_results.append(calculate_metrics(threshold, updated_projects, internal_package_calls))
            visualize_matrix(updated_projects, internal_package_calls, thresholds_name[i])
            visualize_exclusive_package_line_of_codes(updated_projects, thresholds_name[i])
            visualize_projects_jaccard(updated_projects, color, thresholds_name[i])

            meta_data_of_results.append(
                {"threshold": threshold,
                 "remain_projects": [project.name for project in updated_projects],
                 "threshold_output_path": f"outputs/{thresholds_name[i]}",
                 "des": des})

        visualize_metrics(metrics_results, "")
        export_pdf(meta_data_of_results, "outputs/metrics.png", "outputs/lattis.png")
