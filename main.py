from metrics import ssc, rsc
from utils import export_json, jaccard_similarity, decode_content, get_all_packages, calculate_all_jaccard_similarity
from Project import Project
import json
import os
import sys
import glob
from pathlib import Path
from collections import Counter

from visualize_outputs import visualize_projects_jaccard

SOURCE_PATH = str(
    str(os.path.realpath(__file__).replace('\\', '/')).split('SA_G7_1401/')[0]) + 'SA_G7_1401'
if SOURCE_PATH not in sys.path:
    sys.path.append(SOURCE_PATH)

dataset_path = SOURCE_PATH + '/dataset/'
semantics_path = SOURCE_PATH + '/semantics.json'


def load_data():
    projects = metadata_of_projects()
    return projects


def metadata_of_projects():
    projects = []
    all_packages = []
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
        for path, subdirs, files in os.walk(packages_path):
            classes = classes + [name for name in files if ".java" in name]

        if not len(packages) == 0:
            project = Project(name=project_name, packages_path=packages_path,
                              classes=classes, packages=packages, dataset_path=dataset_path)
            projects.append(project)
            all_packages = all_packages + packages
    return projects


def calculate_percentage_of_exclusive_packages(projects: [Project]):
    result = []
    packages = get_all_packages(projects)
    counter = Counter(packages)
    exclusive_packages = [k for k, v in counter.items() if v == 1]
    for project in projects:
        exclusives = 0
        project_packages = project.packages
        for package in project_packages:
            if package in exclusive_packages:
                exclusives = exclusives + 1
        result.append((project, float(exclusives) /
                       float(len(project_packages))))
    return result


def extract_outliers_by_threshold(exclusive_packages_percentage_results, threshold_percentage):
    outliers = []
    for project, exclusive_packages_percentage in exclusive_packages_percentage_results:
        if exclusive_packages_percentage > threshold_percentage:
            outliers.append(project.name)
    return outliers


def generate_empty_packages_relations(projects_metadata):
    info = {}
    for project in projects_metadata:
        variant = project.name
        packages = project.packages
        info[variant] = {key: {} for key in packages}
        for package in packages:
            info[variant][package] = {key: 0 for key in packages if key != package}

    return info


def find_relations(variant: str, path: str, packages: list, relation_model_json: dict):
    contents = []
    # info = {key: couples for key in packages}
    for package in packages:
        files_path_list = glob.glob(f"{path}{package}/*.java")
        for file_path in files_path_list:
            with open(file_path, "rb") as f:
                contents.extend(decode_content(f.readlines()))
            for package_name in packages:
                for content in contents:
                    if f"{variant.lower()}.{package_name}" in content and package != package_name:
                        relation_model_json[variant][package][package_name] += 1
    return relation_model_json


def extract_internal_package_calls(projects: [Project]) -> dict:
    relation_model_json = generate_empty_packages_relations(projects)
    for project in projects:
        variant = project.name
        path = project.packages_path
        packages = project.packages
        relation_model_json = find_relations(variant, path, packages, relation_model_json)

    return relation_model_json


def pipeline():
    projects = load_data()
    ssc(projects)
    # visualize_exclusive_package_line_of_codes(projects)
    # visualize_projects_jaccard(projects)

    jaccards_similarity_projects = calculate_all_jaccard_similarity(projects)

    percentage_of_exclusive_packages = calculate_percentage_of_exclusive_packages(projects)

    outliers = extract_outliers_by_threshold(percentage_of_exclusive_packages, 0.2)

    internal_package_calls = extract_internal_package_calls(projects)

    rsc(internal_package_calls)

    projects_map = [project.get_dict() for project in projects]
    export_json(jaccards_similarity_projects, "jaccards", SOURCE_PATH)
    export_json(projects_map, "semantics", SOURCE_PATH)
    export_json(outliers, "outliers", SOURCE_PATH)
    export_json(internal_package_calls, "internal_package_calls", SOURCE_PATH)


if __name__ == '__main__':
    pipeline()
