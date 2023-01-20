from filter import Filter
from utils import export_json, calculate_all_jaccard_similarity, visualize_projects_jaccard, visualize_exclusive_package_line_of_codes


class Pipeline:
    def __init__(self) -> None:
        self.filters = []
        self.filter = Filter()
        self.projects = []
        self.jaccards_similarity_projects = []

    def execute(self, data_path, source_path):
        self.projects = self.filter.extract_and_load_metadata(data_path)
        visualize_projects_jaccard(self.projects)

        self.jaccards_similarity_projects = calculate_all_jaccard_similarity(
            self.projects)

        percentage_of_exclusive_packages = self.filter.calculate_percentage_of_exclusive_packages(
            self.projects)

        outliers = self.filter.extract_outliers_by_threshold(
            percentage_of_exclusive_packages, 0.2)

        internal_package_calls = self.filter.extract_internal_package_calls(
            self.projects)

        projects_map = [project.get_dict() for project in self.projects]
        visualize_exclusive_package_line_of_codes(self.projects)
        export_json(self.jaccards_similarity_projects,
                    "jaccards", source_path=source_path)
        export_json(projects_map, "semantics", source_path=source_path)
        export_json(outliers, "outliers", source_path=source_path)
        export_json(internal_package_calls,
                    "internal_package_calls", source_path=source_path)
