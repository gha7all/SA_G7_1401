import json


def list_to_json(data, name):
    json_object = json.dumps(data)
    open(f"{name}.json", "w").write(json_object)
