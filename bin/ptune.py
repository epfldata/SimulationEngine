import subprocess
import os

memory = '150000'
duration = 200
modes = ['1']
configs = {100: [10, 50, 100], 1000: [50, 100]}
# configs = {100: [10]}

logFile = open('gameOfLifeLog', 'a')

for width in configs:
  for height in configs[width]:
    for mode in modes:
      sbtCmd = f'project spark; runMain simulation.spark.examples.gameOfLife {width} {height} 200 1'
      # sbtCmd = f'project akka; test:runMain simulation.akka.test.gameOfLifeBench {width} {height} {duration} {mode} Standalone 25251'
      process = subprocess.run(['sbt', '-mem', memory, sbtCmd], text=True, stdout=subprocess.PIPE, check=True)
      print(process.stdout, file=logFile)
      # measured_times = [int(line.split(" ")[-2]) for line in process.stdout.split("\n") if line.find("Round")!=-1]
      # print(f"{width} {height} mode {mode} average time per round {sum(measured_times)/duration}", file = logFile)
      os.system('echo 3 > /proc/sys/vm/drop_caches')
      logFile.flush()

logFile.close()