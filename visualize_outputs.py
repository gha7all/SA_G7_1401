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
    jaccards = calculate_all_jaccard_similarity(projects)[:10]
    print(jaccards)
    G.add_weighted_edges_from(jaccards)
    nx.draw_networkx(G, with_labels=True)
    plt.savefig("filename.png")
