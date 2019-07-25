from bayes_opt import BayesianOptimization
import subprocess
import os, sys


os.chdir('../../..')    #going to the root of the project

def black_box_function(a, b, c):
    result = runCmd("scala target/scala-2.11/economic_simulations_2.11-1.0.jar evaluate")
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

runCmd("sbt clean package")
runCmd("scala target/scala-2.11/economic_simulations_2.11-1.0.jar generate")
pbounds = {'a': (0, 1), 'b': (0, 1), 'c': (0, 1)}
optimizer = BayesianOptimization(black_box_function, pbounds, 10)
optimizer.maximize(init_points = 5, n_iter = 25)

