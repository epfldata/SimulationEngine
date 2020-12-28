package lib.EpistemicLogic

object Utils {
  def ors(es: List[EpistemicSentence]): EpistemicSentence = {
    es.tail.foldLeft(es.head)((x, y) => ImpE(NotE(x), y))
  }

  def ands(es: List[EpistemicSentence]): EpistemicSentence = {
    //    es.tail.foldRight(es.head)((x, y) => NotE(ImpE(x, NotE(y))))
    es.tail.foldLeft(es.head)((x, y) => AndE(x, y))
  }
}
