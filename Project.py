class Project:
    def __init__(self, name, packages_path, classes, packages, dataset_path):
        self.name = name
        self.packages_path = packages_path
        self.classes = classes
        self.packages = packages
        self.dataset_path = dataset_path

    def __str__(self):
        return self.name

    def get_dict(self):
        data = {"name": self.name, "packages_path": self.packages_path,
                "project_path": self.dataset_path + self.name, "classes_count": len(self.classes),
                "packages": self.packages}
        return data
