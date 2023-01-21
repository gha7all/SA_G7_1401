from Project import Project
from utils import get_all_packages


def calculate_ssc(projects: [Project]):
    packages = [project.packages for project in projects]
    intersections = len(list(set.intersection(*map(set, packages))))
    return float(intersections) / float(len(list(set(get_all_packages(projects)))))


def calculate_rsc(internal_package_calls: dict):
    all_calls = []
    for project in internal_package_calls.keys():
        project_calls = []
        for package1 in internal_package_calls[project].keys():
            for package2 in internal_package_calls[project][package1]:
                if internal_package_calls[project][package1][package2] != 0:
                    project_calls.append((package1, package2))

        all_calls.append(project_calls)

    intersections = len(list(set.intersection(*map(set, all_calls))))
    calls = [item for sublist in all_calls for item in sublist]
    return float(intersections) / float(len(calls))


def calculate_metrics(threshold, projects: [Project], internal_package_calls: dict, folder_name: str):
    ssc = round(calculate_ssc(projects), 2)
    svc = 1 - ssc
    rsc = round(calculate_rsc(internal_package_calls), 2)
    rvc = 1 - rsc
    result = {"Threshold": threshold, "SSC": ssc, "SVC": svc,
              "RSC": rsc, "RVC": rvc}
    return result
