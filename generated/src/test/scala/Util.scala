package generated.example.test

object Util {
    def crossJoin[T](list: Traversable[Traversable[T]]): Traversable[Traversable[T]] =
        list match {
            case xs :: Nil => xs map (Traversable(_))
            case x :: xs => for {
                i <- x
                j <- crossJoin(xs)
            } yield Traversable(i) ++ j
    }

    // Comma separated list with just values
    def csList(list: Traversable[Int]): String = {
        list.mkString(",")
    }
}
