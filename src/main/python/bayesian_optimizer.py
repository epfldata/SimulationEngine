from bayes_opt import BayesianOptimization
import subprocess
import os, sys, json

os.chdir('../../..')  # going to the root of the project
json_original = 'params.json'
json_temp = 'temp.json'
json_result = 'result.json'
train_test_ratio = 0.75

def black_box_function(**params):
    f = open(json_temp, "w")
    f.write(json.dumps(params))
    f.close()

    result = runCmd('sbt --warn "run evaluate ' + json_temp + ' ' + str(train_test_ratio) + '"')
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
params = json.loads(f.read())
f.close()

runCmd("sbt clean compile")
runCmd('sbt "run generate ' + json_original + '"')
pbounds = {param: (0, 10 ** 6) for param in params}
optimizer = BayesianOptimization(black_box_function, pbounds, 10)
optimizer.maximize(init_points=10, n_iter=25, acq="poi")
print(optimizer.max)

f = open(json_result, "w")
f.write(json.dumps(optimizer.max['params']))
f.close()

print(runCmd('sbt --warn "run test ' + json_result + ' ' + str(train_test_ratio) + '"'))
