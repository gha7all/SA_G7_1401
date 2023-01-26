import numpy as np
import pandas as pd
from matplotlib import pyplot as plt
import dataframe_image as dfi

import networkx as nx

from utils import *


def visualize_projects_jaccard(projects: List[Project], file_name: str):
    import matplotlib.pyplot as plt
    G = nx.Graph()
    jaccards = calculate_all_jaccard_similarity(projects)
    G.add_weighted_edges_from(jaccards)
    population = {}
    for project in projects:
        population[project.name] = lines_of_code_project(project)

    for i in list(G.nodes()):
        G.nodes[i]['population'] = population[i]

    plt.figure(figsize=(10, 7))

    node_size = [0.2 * nx.get_node_attributes(G, 'population')[v] for v in G]

    edge_width = [5 * G[u][v]['weight'] for u, v in G.edges()]

    nx.draw_networkx(G, node_size=node_size, alpha=0.7,
                     with_labels=True, width=edge_width,
                     edge_color='.4', cmap=plt.cm.Blues)

    plt.axis('off')
    plt.tight_layout()

    plt.savefig(f"outputs/{file_name}/jaccard.png")
    plt.pause(0.1)


def visualize_exclusive_package_line_of_codes(projects: List[Project], file_name: str):
    import matplotlib.pyplot as plt
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
    plt.savefig(f"outputs/{file_name}/exclusive_line_of_codes.png")
    plt.pause(0.1)


def visualize_matrix(projects, internal_package_calls, folder_name: str):
    packages = list(set(get_all_packages(projects)))
    packages.sort()
    apps = []
    for app in internal_package_calls:
        apps.append(app)

    matrix = {}
    colors = []

    for idx, package1 in enumerate(packages):
        matrix[package1] = {}
        colors.append([])
        for package2 in packages:
            isM = True
            isV = False
            for app in apps:
                if package1 not in internal_package_calls[app] or \
                        package2 not in internal_package_calls[app][package1] or \
                        internal_package_calls[app][package1][package2] == 0:
                    isM = False
                if package1 in internal_package_calls[app] and package2 in internal_package_calls[app][package1] and \
                        internal_package_calls[app][package1][package2] != 0:
                    isV = True

            if isM:
                colors[idx].append("#0000FF")
                matrix[package1][package2] = "M"
            elif isV:
                colors[idx].append("#FF0000")
                matrix[package1][package2] = "V"
            elif package1 == package2:
                matrix[package1][package2] = ""
                colors[idx].append("#521456")
            else:
                colors[idx].append("#FFFFFF")
                matrix[package1][package2] = ""

    df = pd.DataFrame(matrix)
    df = df.transpose()
    fig = plt.figure(figsize=(30, 15), dpi=50)
    ax = fig.add_subplot()
    ax.axis('off')
    ax.axis('tight')
    # plt.figure(figsize=(30, 1), dpi=100)
    ax.table(cellText=df.values, colLabels=df.columns, cellLoc='center', loc='center', colLoc='center',
             cellColours=colors, rowLabels=df.columns, )
    plt.savefig(f"outputs/{folder_name}/lattice.png")
    plt.pause(0.1)


def visualize_metrics(results: [dict], folder_name: str):
    ax = plt.subplot(111, frame_on=False)  # no visible frame
    ax.xaxis.set_visible(False)  # hide the x axis
    ax.yaxis.set_visible(False)  # hide the y axis

    dfi.export(pd.DataFrame.from_dict(results), f'outputs{folder_name}/metrics.png', dpi=200)
    plt.pause(0.1)
