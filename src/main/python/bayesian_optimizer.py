from bayes_opt import BayesianOptimization
import subprocess
import os


os.chdir('../../..')    #going to the root of the project

def black_box_function(a, b, c):
    cmd = "sbt --warn \"run evaluate\""
    process = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    (result, error) = process.communicate()

    rc = process.wait()
    if rc != 0:
        print("Error: failed to execute command:", cmd)
        print(error)

    return -float(result.decode("utf-8")[:-1])

pbounds = {'a': (0, 1), 'b': (0, 1), 'c': (0, 1)}
optimizer = BayesianOptimization(black_box_function, pbounds, 10)
optimizer.maximize(init_points = 5, n_iter = 25)

