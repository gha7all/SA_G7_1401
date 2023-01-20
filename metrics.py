from Project import Project


def ssc(projects: [Project]):
    d = [[1, 2, 3, 4], [2, 3, 4], [3, 4, 5, 6, 7]]
    set.intersection(*map(set, d))
