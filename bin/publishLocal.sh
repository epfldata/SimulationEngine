#!/bin/bash

sbt clean
# todo: clean up the build sbt with more a elegant sol
sbt -DpubLocal=true core/publishLocal
sbt -DpubLocal=true akka/publishLocal
sbt -DpubLocal=true base/publishLocal
sbt -DpubLocal=true library/publishLocal
