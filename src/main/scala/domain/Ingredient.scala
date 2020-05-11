package domain

trait  Ingredient {
  def prize: Double
  def abbreviation: String
}

case object Milk extends Ingredient {
  override def prize: Double = 1

  override def abbreviation: String = "milk"
}

case object Sugar extends Ingredient {
  override def prize: Double = 0.5

  override def abbreviation: String = "sugar"
}

case object Soda extends Ingredient {
  override def prize: Double = 0.5

  override def abbreviation: String = "soda"
}

case object Mint extends Ingredient {
  override def prize: Double = 0.5

  override def abbreviation: String = "mint"
}

case object Water extends Ingredient {
  override def prize: Double = 0.5

  override def abbreviation: String = "water"
}

object Ingredient  {

  private def ingredientMap = Map(
    Milk.abbreviation.toLowerCase -> Milk,
    Sugar.abbreviation.toLowerCase ->Sugar,
    Soda.abbreviation.toLowerCase -> Soda,
    Mint.abbreviation.toLowerCase -> Mint,
    Water.abbreviation.toLowerCase ->Water
  )

  def apply(items: Array[String]) = sequence(items.toList.map(s => getIngresdients(s)))

  private def getIngresdients(menu: String) = {
    if (validIngredient(menu)) Right(ingredientMap(menu.toLowerCase))
    else  Left(InvalidIngredient)
  }

  private def validIngredient(ingredient: String) = ingredient.nonEmpty && ingredientMap.keySet.map(_.toLowerCase).contains(ingredient.toLowerCase)

  private def sequence[L, R](listOfEither: List[Either[L, R]]): Either[L, List[R]] = {
    def iterate(remaining: List[Either[L, R]], buffer: Either[L, List[R]]): Either[L, List[R]] = remaining match {
      case Nil => buffer
      case head :: _ if head.isLeft => Left(head.left.get)
      case head :: tail => iterate(tail, Right(buffer.right.get :+ head.right.get))
    }
    iterate(listOfEither, Right(List[R]()))
  }

}

