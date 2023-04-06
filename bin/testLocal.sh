#!/bin/bash

sbt clean
sbt "core / test"
sbt "example / runAll"
sbt "library / test"
sbt "akka / test"
sbt "base / test"
