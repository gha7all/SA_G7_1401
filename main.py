from utils import list_to_json
from Project import Project
import json
import os
import sys
import glob
from pathlib import Path
from collections import Counter


SOURCE_PATH = str(
    str(os.path.realpath(__file__).replace('\\', '/')).split('SA_G7_1401/')[0]) + 'SA_G7_1401'
if SOURCE_PATH not in sys.path:
    sys.path.append(SOURCE_PATH)


attributes = []


def create_metadata_of_projects(dataset_path):
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

    list_to_json([project.get_dict() for project in projects], "semantics")
    jaccards = (calculate_all_jaccard_similarity(projects))
    list_to_json(jaccards, "jaccards")
    exclusive_packages_percentage_result = calculate_percentage_of__exclusive_packages(
        all_packages, projects)
    extract_outliers_by_threshold(exclusive_packages_percentage_result, 0.2)


def calculate_percentage_of__exclusive_packages(all_packages, projects: dict):
    result = []
    counter = Counter(all_packages)
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


def calculate_all_jaccard_similarity(projects_data: [Project]):
    result = []
    project_names = [project.name for project in projects_data]
    for project_name_index1 in range(len(project_names)):
        for project_name_index2 in range(project_name_index1 + 1, len(project_names)):
            jaccard = jaccard_similarity(project_names[project_name_index1],
                                         projects_data[project_name_index1].packages,
                                         project_names[project_name_index2],
                                         projects_data[project_name_index2].packages)
            result.append([project_names[project_name_index1],
                          project_names[project_name_index2], jaccard])
    return result


def jaccard_similarity(project1_name, project1_packages, project2_name, project2_packages):
    if project1_name == project2_name:
        return 1
    intersection = len(
        list(set(project1_packages).intersection(project2_packages)))
    union = (len(set(project1_packages)) +
             len(set(project2_packages))) - intersection
    similarity = float(intersection) / union
    return similarity


def extract_outliers_by_threshold(exclusive_packages_percentage_results, threshold_percentage):
    outlier_result = []
    for project, exclusive_packages_percentage in exclusive_packages_percentage_results:
        if exclusive_packages_percentage > threshold_percentage:
            outlier_result.append(project.name)



def decode_content(content):
    decoded_content = []
    for line in content:
        try:
            decoded_line = line.decode("utf-8").strip().lower()
            if 'import' in decoded_line:
                decoded_content.append(decoded_line)
        except:
            pass
    return decoded_content


def find_relations(variant: str, path: str, packages: list):
    contents = []
    info = {key: 0 for key in packages}
    for package in packages:
        files_path_list = glob.glob(f"{path}{package}/*.java")
        for file_path in files_path_list:
            with open(file_path, "rb") as f:
                contents.extend(decode_content(f.readlines()))
            for content in contents:
                if f"{variant.lower()}.{package}" in content:
                    print(content)
                    info[package] += 1
    return info
            


def extract_relations(dataset_path: str, semantics_path: str) -> dict:
    with open(semantics_path, encoding="utf-8") as f:
        data = json.load(f)

    for item in data:
        variant = item['name']
        path = item['packages_path']
        packages = item['packages']
        result = find_relations(variant, path, packages)
        attributes.append({variant: result})
    
    return attributes
        
        


if __name__ == '__main__':
    dataset_path = SOURCE_PATH + '/dataset/'
    semantics_path = SOURCE_PATH + '/semantics.json'

    # # create_metadata_of_projects(dataset_path)
    # print(extract_relations(dataset_path, semantics_path))
    extract_relations(dataset_path, semantics_path)

