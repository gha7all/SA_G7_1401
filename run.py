from pipeline import Pipeline
import os
import sys
from pipeline import Pipeline


SOURCE_PATH = str(
    str(os.path.realpath(__file__).replace('\\', '/')).split('SA_G7_1401/')[0]) + 'SA_G7_1401'
if SOURCE_PATH not in sys.path:
    sys.path.append(SOURCE_PATH)

dataset_path = SOURCE_PATH + '/dataset/'
source_path = SOURCE_PATH


class Runner:
    def __init__(self) -> None:
        self.jaccards = {}
        self.relations = {}
        self.pipeline = Pipeline()


if __name__ == "__main__":
    Pipeline().execute(dataset_path, source_path)
