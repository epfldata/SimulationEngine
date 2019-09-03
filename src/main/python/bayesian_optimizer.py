import json
import os
import subprocess

import sys
from bayes_opt import BayesianOptimization, UtilityFunction

os.chdir('../../..')  # going to the root of the project
json_original = 'params.json'
json_optimize = "optimize.json"
json_temp = 'temp.json'
json_result = 'result.json'

def toJson(params):
    agentTypes = set()
    for key in params:
        agentTypes.add(key.split("-")[0])
    return {agentType: {
        key.split("-")[1]: params[key] for key in params if key.split("-")[0] == agentType
    } for agentType in agentTypes}

def black_box_function(stepSize, entry, **params):
    all_params.update(toJson(params))
    f = open(json_temp, "w")
    f.write(json.dumps(all_params))
    f.close()

    result = runCmd('sbt --warn "run evaluate {} {} {}'.format(json_temp, stepSize, entry))
    print(result)
    return -float(result.decode("utf-8")[:-1])


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


f = open(json_original, "r")
all_params = json.loads(f.read())
f.close()

f = open(json_optimize, "r")
params = json.loads(f.read())
f.close()

runCmd("sbt clean compile")
stepSizes = [20, 50, 100]
sampleSize = 5
params_result = {"stepSize-{}".format(stepSize): {"entry-{}".format(entry): {} for entry in range(sampleSize)} for stepSize in stepSizes}
for stepSize in stepSizes:
    for entry in range(sampleSize):
        nSteps = 1
        runCmd('sbt "run generate {} {} {} {}"'.format(json_original, sampleSize, nSteps, stepSize))
        pbounds = {}
        for agentType in params:
            append = {
                "number": (1, 200) if agentType == "Person" else (1, 10),
                **{gender: (0, 1) for gender in params[agentType] if "gender" in gender.lower()},
                **{capital: (0, 10000) for capital in params[agentType] if "capital" in capital.lower()},
                **{units: (1, 10) for units in params[agentType] if units in ["buyMu", "buySigma", "consumeMu", "consumeSigma"]},
                **{buyFood: (0, 1) for buyFood in params[agentType] if "buy" in buyFood.lower() and buyFood not in ["buyMu", "buySigma"]},
                **{consumeFood: (0, 1) for consumeFood in params[agentType] if "consume" in consumeFood.lower()
                   and consumeFood not in ["consumeMu", "consumeSigma"]},
                **{enjoyment: (0, 1000) for enjoyment in params[agentType] if "enjoy" in enjoyment.lower()},
                **{edu: (0, 10) for edu in params[agentType] if "edu" in edu.lower()},
                **{bonusSal: (0, 1000) for bonusSal in params[agentType] if "bonussal" in bonusSal.lower()},
                **{salary: (10 ** 4, 10 ** 6) for salary in params[agentType] if "salary" in salary.lower()},
                **{iters: (0, 20) for iters in params[agentType] if "iters" in iters.lower()},
                **{plUnits: (1, 20) for plUnits in params[agentType] if plUnits in ["required", "consumed", "produced", "time"]}
            }
            pbounds = {**pbounds, **{(agentType + "-" + key): append[key] for key in append}}

        optimizer = BayesianOptimization(None, pbounds, 10)
        utility = UtilityFunction(kind="ucb", kappa=2.5, xi=0.0)
        for i in range(20):
            print("stepSize:", stepSize, "entry:", entry, "iteration:", i)
            next_point = optimizer.suggest(utility)
            target = black_box_function(stepSize, entry, **next_point)
            optimizer.register(params=next_point, target=target)
            print()

        print(optimizer.max)

        all_params.update(toJson(optimizer.max['params']))
        params_result["stepSize-{}".format(stepSize)]["entry-{}".format(entry)].update(all_params)

f = open(json_result, "w")
f.write(json.dumps(params_result))
f.close()
