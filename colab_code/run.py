from pipeline import Pipeline

dataset_path = 'dataset/'
source_path = ""


class Runner:
    def __init__(self) -> None:
        self.pipeline = Pipeline()


if __name__ == "__main__":
    Pipeline().execute(dataset_path, source_path)
