from utils import decode_content, get_all_packages
from Project import Project
import os
import glob
from pathlib import Path
from collections import Counter
from typing import Any, List, Tuple


class Filter(object):
    def __init__(self) -> None:
        self.projects = []
        self.all_packages = []
        self.relation_model = {}
        self.relation_model_json = {}
        self.empty_relation_model = {}
        self.content = []
        self.dataset_path = None


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

    def calculate_packages_frequency(self, projects: List[Project]):
        packages = list(set(get_all_packages(projects)))
        packages_frequency = {packages[i]: 0.0 for i in range(0, len(packages))}
        for project in projects:
            for package in project.packages:
                packages_frequency[package] += 1

        for package in packages_frequency.keys():
            packages_frequency[package] = packages_frequency[package] / len(projects)

        return packages_frequency

    def extract_remain_packages_by_threshold(self, packages_frequency: dict, threshold_percentage):
        remain_packages = []
        for package in packages_frequency.keys():
            if float(packages_frequency[package]) >= float(threshold_percentage):
                remain_packages.append(package)
        return remain_packages

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

    def updated_projects_remove_outlier_package(self, remain_packages):
        print(remain_packages)
        updated_projects = self.projects
        for project in updated_projects:
            project.packages = [package for package in project.packages if package in remain_packages]

        return updated_projects
