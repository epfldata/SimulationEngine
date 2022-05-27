#!/bin/bash

# Abort if any occurs any error
set -e

example=$1
test="${example}Test"

compileCmd="project example; runMain example.${example}.Example"
runCmd="project genExample; testOnly generated.example.test.$test"

echo "Compiling example $1..."
echo "sbt $compileCmd"
sbt "$compileCmd"

echo "Running test for $1..."
echo "sbt $runCmd"
sbt "$runCmd"
