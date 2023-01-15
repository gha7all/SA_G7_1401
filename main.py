import itertools
import os
import glob
from pathlib import Path
import statistics
from collections import Counter


def main():
    projects_data = {}
    all_packages = []
    dataset_path = 'dataset/'
    for project_path in [path for path in Path(dataset_path).iterdir() if path.is_dir()]:
        project_name = project_path.name
        main_paths = [path for path in glob.glob(f'{project_path}/*/**/', recursive=True) if
                      str(path).lower().endswith(str(f"/{project_name}/").lower())]
        if len(main_paths) > 1:
            main_paths = [main_path for main_path in main_paths if '/src/' in main_path.lower()]
        packages = [path.name for path in Path(main_paths[0]).iterdir()
                    if path.is_dir() and not path.name.startswith(".")]

        classes = []
        for path, subdirs, files in os.walk(main_paths[0]):
            classes = classes + [name for name in files if ".java" in name]

        if not len(packages) == 0:
            data = {"classes_count": len(classes), "packages": packages}
            all_packages = all_packages + packages
            projects_data[project_name] = data
    # calculate_all_jaccard_similarity(projects_data)
    exclusive_packages_percentage_result = calculate_exclusive_packages(all_packages, projects_data)
    threshold(exclusive_packages_percentage_result, 0.2)


def calculate_exclusive_packages(all_packages, projects_data: dict):
    result = []
    counter = Counter(all_packages)
    exclusive_packages = [k for k, v in counter.items() if v == 1]
    for project in projects_data.keys():
        exclusives = 0
        project_packages = projects_data[project]["packages"]
        for package in project_packages:
            if package in exclusive_packages:
                exclusives = exclusives + 1
        print((project, float(exclusives) / float(len(project_packages))))
        result.append((project, float(exclusives) / float(len(project_packages))))
    return result


def calculate_all_jaccard_similarity(projects_data: dict):
    project_names = list(projects_data.keys())
    for project_name_index1 in range(len(project_names)):
        jaccards = []
        for project_name_index2 in range(len(project_names)):
            jaccard = jaccard_similarity(project_names[project_name_index1],
                                         projects_data[project_names[project_name_index1]],
                                         project_names[project_name_index2],
                                         projects_data[project_names[project_name_index2]])
            jaccards.append(jaccard)
        print(project_names[project_name_index1], statistics.mean(jaccards))


def jaccard_similarity(project_name1, project_data1, project_name2, project_data2):
    if project_name1 == project_name2:
        return 1
    list1 = project_data1["packages"]
    list2 = project_data2["packages"]
    intersection = len(list(set(list1).intersection(list2)))
    union = (len(set(list1)) + len(set(list2))) - intersection
    similarity = float(intersection) / union
    return similarity


def threshold(exclusive_packages_percentage_results, threshold_percentage):
    outlier_result = []
    for project, exclusive_packages_percentage in exclusive_packages_percentage_results:
        if exclusive_packages_percentage > threshold_percentage:
            outlier_result.append(project)
    print(outlier_result)


if __name__ == '__main__':
    main()
