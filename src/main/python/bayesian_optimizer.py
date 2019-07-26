from bayes_opt import BayesianOptimization
import subprocess
import os, sys

os.chdir('../../..')  # going to the root of the project


def black_box_function(foodUnitMu, foodUnitSigma):
    result = runCmd("sbt --warn \"run evaluate " + str(foodUnitMu) + " " + str(foodUnitSigma) + "\"")
    print(result)
    return -float(result.decode("utf-8")[:-1])


def runCmd(cmd):
    print("Run command:", cmd)
    process = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    (result, error) = process.communicate()
    rc = process.wait()
    if rc != 0:
        print("Error: failed to execute command:", cmd)
        print(result, error)
        sys.exit(rc)
    return result


trueMu, trueSigma = 2, 5
runCmd("sbt clean compile")
runCmd("sbt \"run generate " + str(trueMu) + " " + str(trueSigma) + "\"")
pbounds = {'foodUnitMu': (1, 20), 'foodUnitSigma': (0, 20)}
optimizer = BayesianOptimization(black_box_function, pbounds, 10)
optimizer.maximize(init_points=5, n_iter=25)
print(optimizer.max)
