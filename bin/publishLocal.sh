#!/bin/bash

sbt core/publishLocal
sbt akka/publishLocal
sbt base/publishLocal
sbt library/publishLocal
