import subprocess
import os
import statistics

memory = '100000'
freqs = [1, 10, 20, 30, 40]

logFile = open('gameOfLifeFreqLog2', 'w')

for freq in freqs:
    sbtCmd = f'project akka; test:runMain simulation.akka.test.gameOfLifeCommFreq {freq}'
    process = subprocess.run(['sbt', '-mem', memory, sbtCmd], text=True, stdout=subprocess.PIPE, check=True)
    measured_times = [int(line.split(" ")[-2]) for line in process.stdout.split("\n") if line.find("Round")!=-1]
    average = statistics.mean(measured_times)
    stdev = statistics.stdev(measured_times)
    print(f"Frequency {freq} average {average} stdev {stdev}", file = logFile)      
    os.system('echo 3 > /proc/sys/vm/drop_caches')
    logFile.flush()

logFile.close()