package meta.example.subclass_example

//@lift
// Macro expansion failed, no ctor for class
trait ParentClass extends GrandParentClass {
  val parentType: Int = 5
}

trait GrandParentClass {
  val grandParentType: Int = 10
}
