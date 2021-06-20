import subprocess
import statistics
import sys
import os
import itertools
from datetime import datetime, date

class Benchmark:
    def __init__(self, foldername):
        # print(f"Folder name of benchmark is: {foldername}")
        self.foldername = foldername
        self.examples = os.listdir(self.foldername)
        self.summary = {}

    # Dictionary is mutable. The changes to targetDictionary are applied to the original reference
    def safeUpdate(self, targetDictionary, tk, tv):
        assert(len(tv)==3)  # mean, median, perfMetrics

        if (targetDictionary.get(tk) == None or targetDictionary.get(tk)[0] == -1):
            targetDictionary[tk] = tv
        # else:
        #     prevRes = targetDictionary[tk]
        #     newRes = tv
        #     if (newRes[0] != -1):
        #         print("Multiple runs detected!")
        #         multirunRes = [0.5*(prevRes[0] + newRes[0]), 0.5*(prevRes[1] + newRes[1])]
        #         targetDictionary[tk] = multirunRes

    def display(self):
        if (len(self.summary) == 0):
            print("No summary found. Did you run summrize before?")
        else:
            for k in self.summary:
                print(k)
                for mode in self.summary[k]:
                    print(mode)
                    if (mode.find("Container")!=-1):
                        totalContainers = sorted(self.summary[k][mode])
                        for i in totalContainers:
                            print(f"{mode} {i}")
                            agentNumbers = sorted(self.summary[k][mode][i])
                            for j in agentNumbers:
                                print(f"{j}: {self.summary[k][mode][i][j]}")
                    else:
                        agentNumbers = sorted(self.summary[k][mode])
                        iterationTimes = [self.summary[k][mode][i] for i in agentNumbers]
                        for i in agentNumbers:
                            print(f"{i}: {self.summary[k][mode][i]}")

    def checkpointExample(self, example, checkpointDir):
        """Save the summary of the example in a checkpoint file in the given directory"""
        f = open(f"{os.path.join(checkpointDir, example)}.summary", "a")
        f.writelines(f"{example} \n")

        def writeToCkpt(mode, exampleSummary):
            """A helper method to write to the file"""

            agentNumbers = sorted(exampleSummary)
            
            iterationTimes = [exampleSummary[j] for j in agentNumbers]

            averageIterationTimes = [i[0] for i in iterationTimes]
            medianIterationTimes = [i[1] for i in iterationTimes]
            perfMetrics = [i[2] for i in iterationTimes]

            ipcMetrics = [i.get("ipc") for i in perfMetrics]
            llcLoadMisses = [i.get("llc_ld_misses") for i in perfMetrics]
            userTimes = [i.get("user_time") for i in perfMetrics]

            # print(f"Perf metrics {perfMetrics} ipc {ipcMetrics} llc {llcLoadMisses} userTimes {userTimes}")

            # Label size 4 corresponding to following 4 lines per example
            f.writelines(f"Mode: {mode}\n")
            f.writelines(f"Total agents: {agentNumbers}\n")
            f.writelines(f"Time per Iteration [ms] (avg): {averageIterationTimes}\n")
            f.writelines(f"Time per Iteration [ms] (median): {medianIterationTimes}\n")
            f.writelines(f"""IPC: {ipcMetrics}\n""")
            f.writelines(f"""LLC-Load-Misses: {llcLoadMisses}\n""")
            f.writelines(f"""Average user time: {userTimes}\n""")

        for mode in self.summary[example]:
            if (mode.find("Container")!=-1):
                totalContainers = sorted(self.summary[example][mode])
                for i in totalContainers:
                    writeToCkpt(f"{mode}_{i}", self.summary[example][mode][i])
            else:
                writeToCkpt(mode, self.summary[example][mode])

        f.close()
        self.visualizeCheckpoint(f"{os.path.join(checkpointDir, example)}.summary")

    def checkpoint(self):
        if (len(self.summary) == 0):
            print("Unable to find any summary. Did you run summrize before?")
        else:
            now_time = datetime.now().time()  # get time only
            current_time_str = now_time.strftime("%H:%M")

            checkpointDir = os.path.join(self.foldername, f"checkpoint_{current_time_str}")

            os.mkdir(checkpointDir)

            # k: example name
            for k in self.summary:
                self.checkpointExample(k, checkpointDir)

            return checkpointDir

    def visualizeCheckpoint(self, summaryFile):
        import matplotlib.pyplot as plt

        # Summaryfile is the full path of the summary file
        f = open(summaryFile)
        lines = f.readlines()
        f.close()
        totalLines = len(lines)
        exampleName = lines[0]

        # lines of code corresponding to each label
        labelSize = 7

        # Each label has 4 lines
        if ((totalLines - 1) % labelSize != 0):
            raise Exception(f"Error when parsing the summary file {summaryFile}!")

        totalLabels = int((totalLines - 1)/labelSize)

        def stringToMap(s):
            splitted = s.split(":")
            resList = [float(i) for i in splitted[1].strip().strip("][").split(", ")]
            return [splitted[0], resList]

        plt.title(exampleName.capitalize())
        plt.ylabel("Time per Iteration [ms]")
        plt.xlabel("Total Agents")

        plt.xscale('log')

        # Harded code checkpoint summary schema
        for labelCount in range(0, totalLabels):
            mode = lines[labelCount*labelSize + 1].strip()
            totalAgents = stringToMap(lines[labelCount*labelSize + 2])
            avgTime = stringToMap(lines[labelCount*labelSize + 3])
            medianTime = stringToMap(lines[labelCount*labelSize + 4])
            ipc = stringToMap(lines[labelCount*labelSize + 5])
            llc = stringToMap(lines[labelCount*labelSize + 6])
            user_time = stringToMap(lines[labelCount*labelSize + 7])

            plt.plot(totalAgents[1], medianTime[1], marker='*', label=mode.split(":")[1])

        plt.legend()
        figName = summaryFile.split(".")
        plt.savefig(f"{'_'.join(figName)}.png")
        plt.close()


    def processLog(self, filename):
        # Read the log specified by the file name, parse it, and return the median and average
        if (filename.split("/")[-1].startswith("Base")):
            content = subprocess.check_output("""grep "^Total time: " %s | awk '{ print $3 }'""" %(filename), shell=True)
        else:
            content = subprocess.check_output("""grep " Turn " %s | grep " Total time:" | awk '{ print $10 }'""" %(filename), shell=True)

        # Perf metrics
        IPC = subprocess.check_output("""grep "insn per cycle" %s | awk '{ print $4 }'""" %(filename), shell=True).decode('UTF-8').strip()

        LLC_ld_misses = subprocess.check_output("""grep "LLC-load-misses" %s | awk '{ print $1 }'""" %(filename), shell=True).decode('UTF-8').strip()

        user_time = subprocess.check_output("""grep "seconds user" %s | awk '{ print $1 }'""" %(filename), shell=True).decode('UTF-8').strip()

        perfMetrics = {}
        
        # print(f"IPC {IPC} LLC {LLC_ld_misses} user timer {user_time}")

        decodedContent = [int(i) for i in list(filter(None, content.decode('UTF-8').split("\n")))]
        # print(f"Decoded content has size {len(decodedContent)}")
        if (len(decodedContent)==0):
            return [-1, -1, perfMetrics]
        else:
            a = statistics.mean(decodedContent)
            m = statistics.median(decodedContent)

            perfMetrics["ipc"]=float(IPC)
            perfMetrics["llc_ld_misses"]=int("".join(LLC_ld_misses.split(",")))
            perfMetrics["user_time"]=float(user_time)
            return [a, m, perfMetrics]

    # {Total agents, (median, average)}
    def traceSummary(self, example, logs, prefix):
        summary = {}
        rawLog = [i for i in logs if (i.split(".")[0]==prefix and len(i.split("."))==3)]
        
        for log in rawLog:
            totalAgents = int(log.split(".")[2])
            res = self.processLog(os.path.join(self.foldername, example, log))
            self.safeUpdate(summary, totalAgents, res)

        return summary

    def summarizeExample(self, example):
        summary = {}
        mode_key = lambda x: x.split(".")[0]
        container_key = lambda x: x.split(".")[1]

        logs = os.listdir(os.path.join(self.foldername, example))

        # print(f"Example: {example}")
        for md, group in itertools.groupby(logs, mode_key):
            # print(f"Mode key: {md}")
            if (summary.get(md) == None):
                summary[md] = {}

            if (md.find("Container")!=-1):
                for key, group2 in itertools.groupby(group, container_key):
                    # print(f"Container key: {key}")
                    if (summary[md].get(key) == None):
                        summary[md][key] = {}

                    s = self.traceSummary(example, group2, md)
                    for k in s:
                        self.safeUpdate(summary[md][key], k, s[k])

            else:
                s = self.traceSummary(example, group, md)
                for k in s:
                    self.safeUpdate(summary[md], k, s[k])

        self.summary[example] = summary

        return summary

    def summarize(self): 
        for example in self.examples:
            self.summary[example] = self.summarizeExample(example)
    
results = Benchmark("testBenchmark")
results.summarize()
# results.display()
results.checkpoint()