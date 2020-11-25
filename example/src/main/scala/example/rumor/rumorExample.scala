package meta.example.rumor

import meta.deep.runtime.Actor
import squid.quasi.lift
import scala.collection.mutable.ListBuffer 
import scala.util.Random

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val l: ListBuffer[Actor] = new ListBuffer[Actor]()
    val lp: ListBuffer[Person] = new ListBuffer[Person]()

    val env: Env = new Env()
    val closeContacts: Int = 5
    val population: Int = 10000

    val seed: Person = new Gossiper(env, true, 0.5)
    lp.append(seed)

     (1 to population).foreach(i => {
       lp.append(new Gossiper(env, false, 0.3))
     })

    // Everyone is randomly connected to at most $closeContacts others
     lp.toList.foreach(p =>
       p.network ++= ((1 to closeContacts).map(_ => Random.nextInt(population+1)).map(x => lp(x)))
     )

    l.appendAll(lp)
    l.append(env) 
    l.toList 
  }
}

object rumorExample extends App{
  import meta.deep.IR
  import meta.deep.IR.TopLevel.ClassWithObject
  import meta.compile.compileSims

  val cls1: ClassWithObject[Gossiper] = Gossiper.reflect(IR)
  val cls2: ClassWithObject[Env] = Env.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1, cls2), mainClass)
}