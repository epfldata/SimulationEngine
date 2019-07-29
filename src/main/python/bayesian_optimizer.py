from bayes_opt import BayesianOptimization
import subprocess
import os, sys

os.chdir('../../..')  # going to the root of the project


def black_box_function(foodMu, foodSigma, movieMu, movieSigma):
    result = runCmd("sbt --warn \"run evaluate " + str(foodMu) + " " + str(foodSigma) + " " + str(movieMu) + " " + str(movieSigma) + "\"")
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


trueFoodMu, trueFoodSigma = 2, 5
trueMovieMu, trueMovieSigma = 0, 1
runCmd("sbt clean compile")
runCmd("sbt \"run generate " + str(trueFoodMu) + " " + str(trueFoodSigma) + " " + str(trueMovieMu) + " " + str(trueMovieSigma) + "\"")
pbounds = {'foodMu': (1, 20), 'foodSigma': (0, 20), "movieMu": (0, 5), "movieSigma": (0, 5)}
optimizer = BayesianOptimization(black_box_function, pbounds, 10)
optimizer.maximize(init_points=10, n_iter=25, acq="poi")
print(optimizer.max)
