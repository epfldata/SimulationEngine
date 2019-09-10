# economic_simulations
This fork contains the neural network and bayesian optimization implementations. The neural network is supposed to learn the behavior of the simulation, therefore be able to produce the simulation results much faster than the actual simulation. With it's high speed comparing to the actual simulation, it can be used to generated lots of outputs from different initial conditions. This data can later be used to analyze the correlation and possibly the causality between the agents. 

## Architecture and Hierarchy of the Network
Here we use two abstraction levels to model the simulation.
1. Environment Level: In the higher level of abstraction each type of agent from the simulation (e.g. Person, Farm, ...) is represented by an instance of the class Agent. From now on by agent we mean an instance of the class Agent, in other words an agent type from the simulation. An environment (instance of class Environment) keeps the information of all the agents in the world as well as the connections between them. A connection from agent A to agent B means that in the simulation agent B relies on information from agent A. **We also assume by default that each agent has a connection to itslef.** These connections form a graph which is used in the lower level of abstraction.

2. Graph Level: In the lower level of abstraction each type of agent is represented by a node in a graph. There is an edge from node A to node B if and only if there is a connection from agent A to agent B in the environment. These nodes contain the actual neural network used for each agent. 

The environment level is merely a wrapper to make working with the graph level easier for the end user, with no requirement to work with the underlying neural networks.

## Structure of the Agents
In this terminology, we use the term parameter as any field, attribute, property, ... an agent can have. Also since each agent is in fact representing a type of agents in the simulation, the parameter is actually the aggregated parameter over all agents of that type. 

These parameters are divided into two types: **Constants** and **States**.

**Constant** parameters, as their name suggests, remain constant over time. However we can always change them and see how the agent will behave differently.

**State** parameters, however, can change over time. In the simulation, these **state** parameters can be divided into two subtypes: *variables* and *observables*. The important difference between these two subtypes is that *variables* need to be initialized before the first iteration of the simulation and are basically independent from each other, but *observables* can be computed (observed) from *variables*, thus are dependent to them and can not be initialized. However this distinction doesn't exist in both levels of the network environmnet and must be taken care of in the simulation. All the environment recognises are constants and states.

In the underlying node of each agent, the inputs to the node (which are inputs to the node's neural network), are:
1. The constant parameters of the agent
2. The state parameters of any other agent that has a connection into this agent

The outputs of the node (which are the outputs of the node's neural network), are only the corresponing agent's state parameters.

## The general 'Data' Structure
In the python docstrings and in the rest of this page, you may encounter a common pattern for how to pass data to the methods. The general 'data' structure is a dictionary. The keys of this dictionary are the agents, and the values are data of the agents. The data of a specific agent is also another dictionary, where it's keys must be 'states' and 'constants', and the values are pandas dataframes. Each column of these dataframes stores values of a parameter (either a 'state' parameter or a 'constant' parameter) across different samples(rows).

The above general 'data' structure however, is mostly used for inputs. For outputs, since the only available parameters are 'state' parameters, things are a bit easier. Generally the data structure for outputs is a dictionary from agents to pandas dataframes. Each column of these dataframes stores values of a 'state' parameter acros different samples(rows).

## Getting Started
### Configuring and Compiling the Environment
For using the neural network abilities, first of all you have to define the high level environment. You can do it by defining an environemnt instance 

```python
env = Environment()
```
You can add new agents to the environment using `env.register_agents(*agents)` and connections using `env.register_connections(from_agent, *to_agents)`.
After setting up the configuration of the environment using the two methods, you can compile the environment. This makes the environment ready for training.
```python
env.compile()
```
It's important to compile the environment before using it for training, otherwise the low level graph and nodes from agents won't be created.

### Training
There are two different approaches for training. 
#### Solo Training
In the first approach (solo training) each agent is trained individually. This means that for each agent, we try to update its neural network weights according to the loss function computed from its outputs only. However, we still need the whole data of all agents to train each agent individually, since it may have connections from other agents and depend on their data.
You can use this simple method to run the solo training
```python
env.solo_training(train_input, train_output, training_hyper_parameters=None)
```
Both `train_input` and `train_output` have to follow [the general 'data' structure](##the-general-'data'-structure) for inputs and outputs. If you want to manually set hyper parameters for training, the `training_hyper_parameters` should be a dictionary, where its keys are agents and the values are dictionaries containing the values for hyper parameters. The possible hyper parameters to be passed are `batch_size` and `epochs`.

#### Group Training
In the second approach (group training) all agents are trained together. This means that we try to update all weights of different networks simultaneously according to the loss function computed from the aggregated outputs.
You can use this method to run the group training
```python
env.group_train(train_input, train_output, aggregator, epochs=100)
```
`train_input` and `train_output` must have the exact same structure as in the solo training. `aggregator` is an instance of the abstract base class `Aggregator`, which its `aggregate` method must be able to aggregate tensorflow tensors accross different agents into one tensor. This is used for computing the aggregated outputs from individual outputs of agents, thus computing the loss function for training.

### Testing
Testing can be done for evaluating the precision of the training process, as well as comparing this model with other possible models (like using a single feed forward network to predict everything). Just like training, there are two different methods for testing the model.
Solo testing for testing the output of each agent against its own ground truth can be called using the following method. The metric used for testing here is mean absolute error.
```python
env.solo_test(test_input, test_output)
```
Group testing for testing the aggregated output of agents against the aggregated ground truth can be called using the following method. The metric used for teseting here is mean squared error.
```python
env.group_test(test_input, test_output)
```
The structure of `test_input` and `test_output` is the same as `train_input` and `train_output`.

### Saving and Loading Models
After training, you can save the model for later use, saving the model also includes saving the mean and sd of the entire dataset. This mean and sd will later be used for running the same standardization preprocess on data when we want to predict the future or run different analysis.

For saving, you can use the following method
```python
env.save_models(address, data)
```
The data should also be passed to the method for computing the mean and sd.

Before running any analysis or prediction, you need to load the models back into the environment. For doing that, you can use the following method
```python
env.load_models(address)
```
## Using the Analysis Tools
### Prediction
This tool basically receives the current data of the world and predicts the future data. There are two ways to do this.

### Batch Prediction
In this method we give a batch of different initial conditions (input values) to the environment and predict a fixed point of time in the future. The method can be called via
```python
env.predict(data, time=1)
```
The data is a dictionary from agents to each agent's data. The data for each agent is another dictionary where its keys are 'states' and 'constants', and its values are pandas dataframes for the state or constant parameters. Each row in the data indicates a different initial condition. 

This method returns a new data with the same structure, where each row in the returned data is the result of the prediction using the corresponding row in `data` as input, `time` steps into the future.

### Prediction Over Time
In this method we give a single instance of initial conditions to the environment, and receive the time varying data.
```python
env.predict_over_time(data, time=1)
```
The data has the same structure as for batch prediction, but should only contain one row which determines the initial condition. The prediction will continue untill `time`. This method returns a new data with the same structure, where the ith row indicates the predicted data after i steps.

### Input Learning
The input learning mechanism uses data gathered from different agents, aggregates them and uses them for learning back the input parameters. Doing solo-input-learning can also be done in the future, where the inputs of each agent are learned individually using its own network. The following method runs the input learning process.
```python
env.learn_input(agent_output, aggregator, epochs=100, learning_rate=100)
```
The `agent_output` should be a dictionary from agents to their output data as pandas dataframes. This output data is simply just the `state` parameters of the agents. The `aggregator` is the same as the aggregator used for group training, and will aggregate individual states to produce global states. 
This method will return a dictionary from agents to their data. The data of each agent is another dictionary with 'states' and 'constants' as keys and pandas dataframes as values. Each row in these dataframes shows the learned values for the corresponding row in the `agent_output`.

### Correlation Matrix
You can find the correlation between the inputs and outputs of an agent using this method. For computing the correlation matrix of an agent you can call
```python
env.correlation_matrix(agent, iters=1000)
```
The correlation is computed by sampling the inputs `iters` times and computing the outputs, then estimating the correlation from this data. The return type would be a matrix where each column represents an output, each row represents an input and the elements of the matrix are the correlations.

### Derivative Matrix
You can find the partial derivative between a specific output of an agent and all the inputs at different points using this method. By point we mean a combination of values for inputs. 
```python
env.derivative_matrix(agent, param, points=100)
```
The `param` should be any of `agent`'s outputs, in other words one of its `state` parameters. This method will return a dataframe, where each column is the partial derivative of `param` with repsect to the name of the column (which is an input of `agent`), and each row is one of the points.
