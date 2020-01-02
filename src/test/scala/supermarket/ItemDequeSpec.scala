import meta.example.supermarket.ItemDeque
import meta.example.supermarket.goods.{Item1, Item2, Item3}
import org.scalatest._

class ItemDequeSpec extends FlatSpec with Matchers {

  "Item deque" should "be able to construct from an empty parameter list" in {
    val newDeque = new ItemDeque()
    newDeque should have size 0
  }

  "Item deque" should "be able to construct from a list of Item" in {
    val item1 = new Item1
    val item2 = new Item2
    val item3 = new Item3
    val newDeque = new ItemDeque(Vector(item1, item2, item3))
    newDeque should have size 3
  }

  "Item deque" should "be able to construct from a single Item" in {
    val item1 = new Item1
    val newDeque = new ItemDeque(item1)
    newDeque should have size 1
  }

  "Item deque +=" should "add an Item to the queue" in {
    val emptyDeque = new ItemDeque()
    emptyDeque+=(new Item1)
    emptyDeque should have size 1
  }

  "Item deque +=" should "add a list of Item to the queue" in {
    val emptyDeque = new ItemDeque()
    emptyDeque += Vector(new Item1, new Item2, new Item3)
    emptyDeque should have size 3
    emptyDeque += Vector(new Item1, new Item2)
    emptyDeque should have size 5
  }

  "Item deque popLeft" should "remove the first inserted Item" in {
    val item1 = new Item1
    val item2 = new Item2
    val item3 = new Item3
    val newDeque = new ItemDeque(Vector(item1, item2, item3))
    newDeque.popLeft should be (item1)
    newDeque should have size 2
  }

  "Item deque popRight" should "remove the last inserted Item" in {
    val item1 = new Item1
    val item2 = new Item2
    val item3 = new Item3
    val newDeque = new ItemDeque(Vector(item1, item2, item3))
    newDeque.popRight should be (item3)
    newDeque should have size 2
    newDeque += Vector(item1, item2)
    newDeque should have size 4
    newDeque.popRight should be (item2)
  }

  "Remove from an empty item deque" should "throw NoSuchElementException" in {
    val emptyDeque = new ItemDeque()
    a [NoSuchElementException] should be thrownBy {
      emptyDeque.popLeft
    }
    a [NoSuchElementException] should be thrownBy {
      emptyDeque.popRight
    }
  }
}
