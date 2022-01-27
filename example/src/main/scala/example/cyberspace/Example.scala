package example
package cyberspace

object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(populationSize: Int, totalServers: Int, syncPeriod: Int, messagesPerSync: Int): List[Actor] = {
            
            val readerRatio = 0.9
            val participantRatio = 0.09
            val superUserRatio = 0.01
            
            val servers = (1 to totalServers).toList.map(x => new Server(syncPeriod, messagesPerSync))

            servers.foreach(s => s.allServers = servers)

            val perServerPopulation = populationSize / totalServers

            servers.flatMap(x => {
                    x :: ((0 to (perServerPopulation * readerRatio).toInt).map(r => {
                        new Reader(x)
                    }) ++
                    (0 to (perServerPopulation * participantRatio).toInt).map(r => {
                        new Participant(x)
                    }) ++
                    (0 to (perServerPopulation * superUserRatio).toInt).map(r => {
                        new SuperUser(x)
                    })).toList
            })
        }
    }
}

object Example extends App {

  val cls1: ClassWithObject[Reader] = Reader.reflect(IR)
  val cls2: ClassWithObject[Participant] = Participant.reflect(IR)
  val cls3: ClassWithObject[SuperUser] = SuperUser.reflect(IR)
  val cls4: ClassWithObject[Server] = Server.reflect(IR)

  val mainClass = MainInit.liftedMain
    
  compileSims(List(cls1, cls2, cls3, cls4), Some(mainClass))
}