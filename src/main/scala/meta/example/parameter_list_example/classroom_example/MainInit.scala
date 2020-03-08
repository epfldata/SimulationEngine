package meta.example.parameter_list_example.classroom_example
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val advisor = new teacher("Church")
    val student = new student("Turing", advisor)
    List(advisor, student)
  }
}
