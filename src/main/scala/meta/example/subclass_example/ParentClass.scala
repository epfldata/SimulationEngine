package meta.example.subclass_example

trait ParentClass extends GrandParentClass {
  var parentToken: Int = 5

  override def hello: Unit = println("Hello from parent class!")
}

trait GrandParentClass {
  var grandParentToken: Int = 10

  def hello: Unit = println("Hello from grandparent class!")
}
