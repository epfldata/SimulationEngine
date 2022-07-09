#!/bin/bash

set -e

sbt clean
sbt "project core; test"
sbt "project genCore; test"
sbt "project example; runAll"
sbt "project genExample; testOnly generated.example.test.generatedExamples"
