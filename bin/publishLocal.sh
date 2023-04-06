#!/bin/bash

sbt clean
# todo: find a more elegant solution
sbt -DpubLocal=true core/publishLocal
sbt -DpubLocal=true akka/publishLocal
sbt -DpubLocal=true base/publishLocal
sbt -DpubLocal=true library/publishLocal
