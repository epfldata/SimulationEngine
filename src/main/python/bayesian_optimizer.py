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

def black_box_function(**params):
    all_params.update(params)
    f = open(json_temp, "w")
    f.write(json.dumps(all_params))
    f.close()

    result = runCmd('sbt --warn "run evaluate ' + json_temp + '"')
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
runCmd('sbt "run generate ' + json_original + '"')
pbounds = {
    **{gender: (0, 1) for gender in params if "gender" in gender.lower()},
    **{capital: (0, 10000) for capital in params if "capital" in capital.lower()},
    **{units: (1, 10) for units in params if units in ["buyMu", "buySigma", "consumeMu", "consumeSigma"]},
    **{buyFood: (0, 1) for buyFood in params if "buy" in buyFood.lower() and buyFood not in ["buyMu", "buySigma"]},
    **{consumeFood: (0, 1) for consumeFood in params if "consume" in consumeFood.lower()
       and consumeFood not in ["consumeMu", "consumeSigma"]},
    **{enjoyment: (1, 1000) for enjoyment in params if "enjoy" in enjoyment.lower()},
    **{edu: (1, 10) for edu in params if "edu" in edu.lower()},
    **{bonusSal: (0, 1000) for bonusSal in params if "bonussal" in bonusSal.lower()},
    **{salary: (10 ** 4, 10 ** 6) for salary in params if "salary" in salary.lower()},
    **{iters: (0, 20) for iters in params if "iters" in iters.lower()},
    **{plUnits: (1, 50) for plUnits in params if plUnits in
       ["farmReq", "farmProd", "millCons", "millProd", "bakeryCons", "bakeryProd", "cattleReq", "cattleProd",
        "oilReq", "oilProd", "refineryCons", "refineryProd"
        ]},
    **{time: (1, 100) for time in params if "time" in time.lower()}
}

optimizer = BayesianOptimization(None, pbounds, 10)
utility = UtilityFunction(kind="ucb", kappa=2.5, xi=0.0)
for i in range(20):
    print("iteration:", i)
    next_point = optimizer.suggest(utility)
    target = black_box_function(**next_point)
    optimizer.register(params=next_point, target=target)
    print()

print(optimizer.max)

all_params.update(optimizer.max['params'])
f = open(json_result, "w")
f.write(json.dumps(all_params))
f.close()
