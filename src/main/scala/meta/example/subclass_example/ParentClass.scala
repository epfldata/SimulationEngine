package meta.example.subclass_example

//@lift
// Macro expansion failed, no ctor for class
trait ParentClass extends GrandParentClass {
  var parentType: Int = 5
}

trait GrandParentClass {
  var grandParentType: Int = 10
}
