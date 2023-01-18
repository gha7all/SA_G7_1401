import glob

from Project import Project
from utils import lines_of_code_project, exclusive_packages_count, get_all_packages, calculate_all_jaccard_similarity
import matplotlib.pyplot as plt
import numpy as np
import networkx as nx


def visualize_exclusive_package_line_of_codes(projects: [Project]):
    lines_of_codes = []
    exclusive_packages_counts = []

    packages = get_all_packages(projects)
    for project in projects:
        lines_of_codes.append(lines_of_code_project(project))
        exclusive_packages_counts.append(exclusive_packages_count(project, packages))

    x_points = np.array(lines_of_codes)
    y_points = np.array(exclusive_packages_counts)

    plt.xlabel("Lines of Code")
    plt.ylabel("Exclusive Packages")

    plt.plot(x_points, y_points, 'o')
    plt.show()


def visualize_projects_jaccard(projects: [Project]):
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
