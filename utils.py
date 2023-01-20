import glob
import json
from collections import Counter
from Project import Project
import matplotlib.pyplot as plt
import numpy as np
import networkx as nx

from Project import Project
from typing import List


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


def exclusive_packages_count(project: Project, packages: List[str]):
    exclusives_count = 0
    counter = Counter(packages)
    exclusive_packages = [k for k, v in counter.items() if v == 1]
    for package in project.packages:
        if package in exclusive_packages:
            exclusives_count = exclusives_count + 1
    return exclusives_count


def get_all_packages(projects: List[Project]):
    packages = []
    for project in projects:
        packages += project.packages

    return list(set(packages))


def calculate_all_jaccard_similarity(projects_data: List[Project]):
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


def visualize_exclusive_package_line_of_codes(projects: List[Project]):
    lines_of_codes = []
    exclusive_packages_counts = []

    packages = get_all_packages(projects)
    for project in projects:
        lines_of_codes.append(lines_of_code_project(project))
        exclusive_packages_counts.append(
            exclusive_packages_count(project, packages))

    x_points = np.array(lines_of_codes)
    y_points = np.array(exclusive_packages_counts)

    plt.xlabel("Lines of Code")
    plt.ylabel("Exclusive Packages")

    plt.plot(x_points, y_points, 'o')
    plt.show()


def visualize_projects_jaccard(projects: List[Project]):
    G = nx.Graph()
    jaccards = calculate_all_jaccard_similarity(projects)
    print(len(jaccards))
    G.add_weighted_edges_from(jaccards)
    population = {}
    for project in projects:
        population[project.name] = lines_of_code_project(project)

    for i in list(G.nodes()):
        G.nodes[i]['population'] = population[i]

    plt.figure(figsize=(10, 7))

    node_size = [0.2 * nx.get_node_attributes(G, 'population')[v] for v in G]
    # size of node is a list of population of cities

    edge_width = [5 * G[u][v]['weight'] for u, v in G.edges()]
    # width of edge is a list of weight of edges

    nx.draw_networkx(G, node_size=node_size, alpha=0.7,
                     with_labels=True, width=edge_width,
                     edge_color='.4', cmap=plt.cm.Blues)

    plt.axis('off')
    plt.tight_layout()

    plt.savefig("filename.png")
