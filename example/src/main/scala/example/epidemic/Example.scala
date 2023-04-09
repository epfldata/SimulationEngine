package example
package epidemic

object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(population: Int, p: Double): List[Actor] = {
            val socialGraph = cloudcity.lib.Graph.GenerateGraph.ErdosRenyiGraph(population, p)
            val citizens = socialGraph.map(i => {
                val person = new Person(Random.nextInt(90) + 10)
                person.id = i._1
                (i._1, person)
            })

            citizens.map(c => {
                c._2.connectedAgents = socialGraph(c._1).map(i => citizens(i))
            })
            citizens.values.toList
        }
    }
}

object Example extends App {

  val cls1: ClassWithObject[Person] = Person.reflect(IR)

  val mainClass = MainInit.liftedMain
    
  compileSims(List(cls1), Some(mainClass))
}