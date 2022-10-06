import subprocess

memory = '100000'
duration = 200
modes = ['1']
configs = {100: [10, 100], 1000: [100, 1000]}
# configs = {1000: [1000]}
# configs = {10: [10]}
for width in configs:
  for height in configs[width]:
    for mode in modes:
      sbtCmd = f'project akka; test:runMain simulation.akka.test.gameOfLifeBench {width} {height} {duration} {mode} Standalone 25251'
      process = subprocess.run(['sbt', '-mem', memory, sbtCmd], text=True, stdout=subprocess.PIPE, check=True)
      measured_times = [int(line.split(" ")[-2]) for line in process.stdout.split("\n") if line.find("Round")!=-1]
      print(f"{width} {height} mode {mode} average time per round {sum(measured_times)/duration}")