
# -*- coding: utf-8 -*-

import sys
import fileinput

confRoot="Akka/src/main/resources/"

if __name__=="__main__":
    opts = [opt for opt in sys.argv[1:] if opt.startswith("-")]
    args = [arg for arg in sys.argv[1:] if not arg.startswith("-")]

    if (len(args) < 1):
        print("""Please enter the name of the config file, keys, and value to be set
Note that all config files are assumed to be located under Akka/src/main/resources/
Example:
driver-worker.conf workers-per-machine 10
        """)
    else:
        filename = sys.argv[1]
        key_to_search = sys.argv[2]
        replacement_value = sys.argv[3]

        with fileinput.FileInput(confRoot+filename, inplace=True, backup='.bak') as file:
            for line in file:
                if (line.find(key_to_search)!=-1):
                    search_value = line.split("=")[-1]
                    print(line.replace(search_value, replacement_value), end='\n')
                else:
                    print(line, end='')