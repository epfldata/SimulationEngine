#!/bin/bash

# Sort by the java's uptime. The new process (forked child) shows on top
get_java_pids () {
  javaPids=`ps aux --sort=etime | grep java | grep -v grep | tr -s ' ' | awk '{ print $2 }'`
}

# Stop all the java instances
clean_java () {
  get_java_pids
  lines=`wc -l <<< $javaPids`

  if [ $lines -ne 0 ]; then
    for p in $javaPids
    do
      kill -9 $p
    done
  fi
}

heights=(10)

examples=("wator" "epidemic" "segregation" "gameOfLife")

containers=(50)

modes=("Akka" "AkkaContainer")
# modes=("Base" "BaseContainer" "Akka" "AkkaContainer")

# Message caching, direct method, vanilla
containerOpt=("MC" "DM" "VN")

benchmarkDir="testBenchmark"

totalTurn=10

# 200g on server
testmem=200000

for example in "${examples[@]}"
do 
    mkdir -p $benchmarkDir/$example

    for mode in "${modes[@]}"
    do 
        for height in "${heights[@]}"
        do
          population="$((100 * height))"

          case $mode in
          *Container)             
              for container in "${containers[@]}"
              do 
                  for opt in "${containerOpt[@]}"
                  do 
                    echo "Example $example Mode $mode Containers $container Optimization $opt Height $height"

                    logFile="$benchmarkDir/$example/$mode$opt.c$container.$population"

                    sbtCmd="project genExample; test:runMain generated.example.test.benchmarkTest $example $totalTurn $mode $container $opt $height $population"

                    perf stat -d sbt -mem $testmem "$sbtCmd" > $logFile 2>&1 

                    clean_java
                  done
              done
              ;;
          *) 
              echo "Example $example Mode $mode Container 0 Optimization 0 Height: $height"

              logFile="$benchmarkDir/$example/$mode.c0.$population"

              sbtCmd="project genExample; test:runMain generated.example.test.benchmarkTest $example $totalTurn $mode 0 0 $height $population"

              perf stat -d sbt -mem $testmem "$sbtCmd" > $logFile 2>&1 

              clean_java
          ;;
          esac
        done
    done
done