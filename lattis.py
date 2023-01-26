import random

import networkx as nx
import matplotlib.pyplot as plt

from Project import Project
from utils import get_all_packages


def draw_lattis(internal_package_calls: dict, projects: [Project], file_name: str):
    all_tuple_calls = []
    packages_calls = {}
    for package in get_all_packages(projects):
        packages_calls[package] = [[], []]
    for project in internal_package_calls.keys():
        for package1 in internal_package_calls[project].keys():
            for package2 in internal_package_calls[project][package1]:
                if internal_package_calls[project][package1][package2] != 0:
                    packages_calls[package1][0] += [package2]
                    packages_calls[package2][1] += [package1]
                    all_tuple_calls.append((package1, package2))

    for package in packages_calls.keys():
        packages_calls[package][0] = list(set(packages_calls[package][0]))
        packages_calls[package][1] = list(set(packages_calls[package][1]))

    all_similar_packages_structure = find_similar_packages_structure(packages_calls)
    change_name_of_similar_packages = {}
    for similar_packages in all_similar_packages_structure:
        for package in similar_packages:
            change_name_of_similar_packages[package] = "\n".join(similar_packages)

    for index, tuple_call in enumerate(all_tuple_calls):
        if tuple_call[0] in change_name_of_similar_packages:
            all_tuple_calls[index] = (change_name_of_similar_packages[tuple_call[0]], tuple_call[1])
        if tuple_call[1] in change_name_of_similar_packages:
            all_tuple_calls[index] = (tuple_call[0], change_name_of_similar_packages[tuple_call[0]])

    graph(all_tuple_calls, file_name)


def find_indices(l, value, index):
    duplicates = [
                     i for i, item in enumerate(l)
                     if item == value and i != index
                 ] + [index]
    duplicates.sort()
    return duplicates if len(duplicates) > 1 else []


def graph(all_tuple_calls, file_name: str):
    g = nx.DiGraph()
    g.add_edges_from(all_tuple_calls)

    colors = [random.choice(['tab:red', 'tab:orange', 'tab:olive', 'tab:green', 'tab:purple']) for i in
              range(len(g.nodes))]
    plt.figure(figsize=(100, 100), dpi=30)
    nx.draw_networkx(g, with_labels=True, arrowsize=100, width=10, arrows=True,
                     node_color=colors, font_size=100, node_size=100000)
    plt.savefig(f"{file_name}.png")
    plt.clf()


def find_similar_packages_structure(packages_calls: dict):
    all_similar_packages = []
    for i, calls in enumerate(packages_calls.values()):
        similar_packages = find_indices(list(packages_calls.values()), calls, i)
        similar_packages = [list(packages_calls.keys())[i] for i in similar_packages]
        if len(similar_packages) != 0:
            if similar_packages not in all_similar_packages:
                all_similar_packages.append(similar_packages)

    return all_similar_packages
