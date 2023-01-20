from utils import decode_content, get_all_packages
from Project import Project
import json
import os
import glob
from pathlib import Path
from collections import Counter
from typing import Any, List, Tuple


class Filter(object):
    def __init__(self) -> None:
        self.projects = []
        self.all_packages = []
        self.percentages_results = []
        self.outliers = []
        self.relation_model = {}
        self.relation_model_json = {}
        self.empty_relation_model = {}
        self.content = []
        self.dataset_path = None

    def process(self, message):
        pass

    def extract_and_load_metadata(self, dataset_path: str):
        for project_path in [path for path in Path(dataset_path).iterdir() if path.is_dir()]:
            project_name = project_path.name
            main_paths = [path for path in glob.glob(f'{project_path}/*/**/', recursive=True) if
                          str(path).lower().endswith(str(f"/{project_name}/").lower())]
            if len(main_paths) > 1:
                main_paths = [
                    main_path for main_path in main_paths if '/src/' in main_path.lower()]
            packages_path = main_paths[0]
            packages = [path.name for path in Path(packages_path).iterdir()
                        if path.is_dir() and not path.name.startswith(".")]

            classes = []
            for _, _, files in os.walk(packages_path):
                classes = classes + [name for name in files if ".java" in name]

            if not len(packages) == 0:
                project = Project(name=project_name, packages_path=packages_path,
                                  classes=classes, packages=packages, dataset_path=dataset_path)
                self.projects.append(project)
                self.all_packages = self.all_packages + packages
        return self.projects

    def calculate_percentage_of_exclusive_packages(self, projects: List[Project]):
        packages = get_all_packages(projects)
        counter = Counter(packages)
        exclusive_packages = [k for k, v in counter.items() if v == 1]
        for project in projects:
            exclusives = 0
            project_packages = project.packages
            for package in project_packages:
                if package in exclusive_packages:
                    exclusives = exclusives + 1
            self.percentages_results.append((project, float(exclusives) /
                                             float(len(project_packages))))
        
        return self.percentages_results

    def extract_outliers_by_threshold(self, exclusive_packages_percentage_results, threshold_percentage):
        for project, exclusive_packages_percentage in exclusive_packages_percentage_results:
            if exclusive_packages_percentage > threshold_percentage:
                self.outliers.append(project.name)
        return self.outliers

    def generate_empty_packages_relations(self, projects_metadata) -> dict:
        for project in projects_metadata:
            variant = project.name
            packages = project.packages
            self.relation_model[variant] = {key: {} for key in packages}
            for package in packages:
                self.relation_model[variant][package] = {
                    key: 0 for key in packages if key != package}
        return self.relation_model

    def find_relations(self, variant: str, path: str, packages: list, relation_model: dict) -> dict:
        for package in packages:
            files_path_list = glob.glob(f"{path}{package}/*.java")
            for file_path in files_path_list:
                with open(file_path, "rb") as f:
                    self.content.extend(decode_content(f.readlines()))
                for package_name in packages:
                    for content in self.content:
                        if f"{variant.lower()}.{package_name}" in content and package != package_name:
                            relation_model[variant][package][package_name] += 1
        
        self.relation_model_json = relation_model
        return self.relation_model_json

    def extract_internal_package_calls(self, projects: List[Project]) -> dict:
        self.relation_model_json = self.generate_empty_packages_relations(projects)
        for project in projects:
            variant = project.name
            path = project.packages_path
            packages = project.packages
            self.relation_model_json = self.find_relations(
                variant, path, packages, self.relation_model_json)

        return self.relation_model_json
