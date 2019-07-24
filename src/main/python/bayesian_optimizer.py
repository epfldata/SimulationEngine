from bayes_opt import BayesianOptimization
import subprocess
import os


os.chdir('../../..')    #going to the root of the project

def black_box_function(a, b, c):
    result = subprocess.run(['scala', 'target/scala-2.11/economic_simulations_2.11-1.0.jar'], stdout=subprocess.PIPE)
    return -float(result.stdout.decode("utf-8")[:-1])


pbounds = {'a': (0, 1), 'b': (0, 1), 'c': (0, 1)}
optimizer = BayesianOptimization(black_box_function, pbounds, 10)
optimizer.maximize()

