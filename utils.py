import glob
import json
from collections import Counter

from Project import Project


def export_json(data, name, source_path):
    json_object = json.dumps(data)
    open(source_path + f"/outputs/{name}.json", "w").write(json_object)


def jaccard_similarity(project1_name, project1_packages, project2_name, project2_packages):
    if project1_name == project2_name:
        return 1
    intersection = len(
        list(set(project1_packages).intersection(project2_packages)))
    union = (len(set(project1_packages)) +
             len(set(project2_packages))) - intersection
    similarity = float(intersection) / union
    return similarity


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


def lines_of_code_project(project: Project):
    classes_path = []
    for package in project.packages:
        classes_path += glob.glob(f"{project.packages_path}{package}/*.java")
    lines_of_code = 0
    for class_path in classes_path:
        with open(class_path, "rb") as f:
            lines_of_code += len(f.readlines())
    return lines_of_code


def exclusive_packages_count(project: Project, packages: [str]):
    exclusives_count = 0
    counter = Counter(packages)
    exclusive_packages = [k for k, v in counter.items() if v == 1]
    for package in project.packages:
        if package in exclusive_packages:
            exclusives_count = exclusives_count + 1
    return exclusives_count


def get_all_packages(projects: [Project]):
    packages = []
    for project in projects:
        packages += project.packages

    return list(set(packages))


def calculate_all_jaccard_similarity(projects_data: [Project]):
    result = []
    project_names = [project.name for project in projects_data]
    for project_name_index1 in range(len(project_names)):
        for project_name_index2 in range(project_name_index1 + 1, len(project_names)):
            jaccard = jaccard_similarity(project_names[project_name_index1],
                                         projects_data[project_name_index1].packages,
                                         project_names[project_name_index2],
                                         projects_data[project_name_index2].packages)
            result.append((project_names[project_name_index1],
                           project_names[project_name_index2], jaccard))
    return result
