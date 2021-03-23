package meta 

import Console.{RED, RESET, YELLOW, UNDERLINED}

object Util {
    val warning: (String) => Unit = 
        (x: String) => Console.println(s"${RESET}${YELLOW}${UNDERLINED}WARNING${RESET} ${YELLOW}${x}${RESET}")
}