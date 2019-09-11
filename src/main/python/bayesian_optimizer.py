import json
import os
import subprocess

import sys
from bayes_opt import BayesianOptimization, UtilityFunction


def toJson(params):
    agentTypes = {
        "constants": set(),
        "variables": set()
    }
    for key in params:
        split = key.split("-")
        agentTypes[split[0]].add(split[1])
    return {
        type: {
            agentType: {
                key.split("-")[2]: params[key] for key in params if key.split("-")[0:2] == [type, agentType]
            } for agentType in agentTypes[type]
        } for type in agentTypes
    }


def black_box_function(stepSize, entry, **params):
    all_params.update(toJson(params))
    f = open(json_temp, "w")
    f.write(json.dumps(all_params))
    f.close()

    result = runCmd('sbt --warn "run evaluate {} {} {}"'.format(json_temp, stepSize, entry))
    target = -float(result.decode("utf-8")[:-1])
    print(target)
    return target


def runCmd(cmd):
    print("Run command:", cmd)
    process = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    (result, error) = process.communicate()
    rc = process.wait()
    if rc != 0:
        print("Error: failed to execute command:", cmd)
        print(result.decode("utf-8"), error.decode("utf-8"))
        sys.exit(rc)
    return result


if __name__ == '__main__':
    os.chdir('../../..')  # going to the root of the project
    json_original = 'params.json'
    json_optimize = "optimize.json"
    json_temp = 'temp.json'
    json_result = 'result.json'

    f = open(json_original, "r")
    all_params = json.loads(f.read())
    f.close()

    f = open(json_optimize, "r")
    params = json.loads(f.read())
    f.close()

    pbounds = {}
    constants = params["constants"]
    variables = params["variables"]
    for agentType in constants:
        append = {
            "number": (1, 200) if agentType == "Person" else (1, 10),
            **{gender: (0, 1) for gender in constants[agentType] if "gender" in gender.lower()},
            **{units: (1, 10) for units in constants[agentType] if units in ["buyMu", "buySigma", "consumeMu", "consumeSigma"]},
            **{buyFood: (0, 1) for buyFood in constants[agentType] if "buy" in buyFood.lower() and buyFood not in ["buyMu", "buySigma"]},
            **{consumeFood: (0, 1) for consumeFood in constants[agentType] if "consume" in consumeFood.lower()
               and consumeFood not in ["consumeMu", "consumeSigma"]},
            **{enjoyment: (0, 1000) for enjoyment in constants[agentType] if "enjoy" in enjoyment.lower()},
            **{edu: (0, 10) for edu in constants[agentType] if "edu" in edu.lower()},
            **{bonusSal: (0, 1000) for bonusSal in constants[agentType] if "bonussal" in bonusSal.lower()},
            **{salary: (10 ** 4, 10 ** 6) for salary in constants[agentType] if "salary" in salary.lower()},
            **{iters: (0, 20) for iters in constants[agentType] if "iters" in iters.lower()},
            **{plUnits: (1, 20) for plUnits in constants[agentType] if plUnits in ["required", "consumed", "produced", "time"]}
        }
        pbounds = {**pbounds, **{("constants-{}-{}".format(agentType, key)): append[key] for key in append}}
    for agentType in variables:
        append = {
            **{capital: (0, 10000) for capital in variables[agentType] if "capital" in capital.lower()},
            **{value_destroyed: (0, 100) for value_destroyed in variables[agentType] if "value_destroyed" in value_destroyed.lower()},
            **{happiness: (0, 100) for happiness in variables[agentType] if "happiness" in happiness.lower()},
            **{salary: (10 ** 4, 10 ** 6) for salary in variables[agentType] if "salary" in salary.lower()}
        }
        pbounds = {**pbounds, **{("variables-{}-{}".format(agentType, key)): append[key] for key in append}}

    stepSizes = [20, 50, 100]
    sampleSize = 5
    nSteps = 1
    optimization_iters = 20
    params_result = {"stepSize-{}".format(stepSize): {"entry-{}".format(entry): {} for entry in range(sampleSize)} for stepSize in stepSizes}

    runCmd("sbt clean compile")
    for stepSize in stepSizes:
        runCmd('sbt "run generate {} {} {} {}"'.format(json_original, sampleSize, nSteps, stepSize))
        for entry in range(sampleSize):
            optimizer = BayesianOptimization(None, pbounds, 10)
            utility = UtilityFunction(kind="ucb", kappa=2.5, xi=0.0)
            for i in range(optimization_iters):
                print("stepSize:", stepSize, "entry:", entry, "iteration:", i)
                next_point = optimizer.suggest(utility)
                target = black_box_function(stepSize, entry, **next_point)
                optimizer.register(params=next_point, target=target)
                print()

            all_params.update(toJson(optimizer.max['params']))
            params_result["stepSize-{}".format(stepSize)]["entry-{}".format(entry)].update(all_params)
            params_result["stepSize-{}".format(stepSize)]["entry-{}".format(entry)]["target"] = optimizer.max["target"]

            f = open(json_result, "w")
            f.write(json.dumps(params_result))
            f.close()
