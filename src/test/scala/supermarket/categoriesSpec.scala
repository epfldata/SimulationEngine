import meta.example.supermarket.categories.getCategoryNames
import meta.example.supermarket.{categories, categoryAmount, utils}
import org.scalatest._

class categoriesSpec extends FlatSpec with Matchers {

  "Total count in categories" should "work" in {
    categories.getCnt should be (5)
  }

  "Parameter list of categoryAmount" should "match the category names" in {
    assert(utils.ccArgToVector(categoryAmount()).map(x => x._1).equals(getCategoryNames))
  }

  "getArticleNames" should "return the name of articles defined" in {
    categories.getArticleNames("Vegetable") should be
    List("Eggplant", "Potato", "Onion", "Broccoli", "Cucumber", "Carrots")
  }

  "getArticleUnit" should "return the priceUnit for the given article" in {
    categories.getArticleUnit("Cheese") should be (200)
    categories.getArticleUnit("Kitkat") should be (300)
  }
}