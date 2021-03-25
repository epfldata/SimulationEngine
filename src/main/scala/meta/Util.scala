package meta 

import Console.{RED, RESET, YELLOW, UNDERLINED}

object Util {
    val warning: (String) => Unit = 
        (x: String) => Console.println(s"${RESET}${YELLOW}${UNDERLINED}WARNING${RESET} ${YELLOW}${x}${RESET}")

    val debug: (String) => Unit = 
        (x: String) => 
            if (meta.compile.Development.debug){
                Console.println(s"${RESET}${RED}DEBUG${RESET}${x}\n\n")
            } 
}