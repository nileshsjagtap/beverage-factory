package domain

trait Menu  {

  def ingredients : List[Ingredient]
  def prize : Double
  def abbreviation: String

}

case object Coffee extends Menu {
  override def ingredients: List[Ingredient] = List(Milk,Sugar,Water)

  override def prize: Double = 5

  override def abbreviation: String = "coffee"
}

case object Chai extends Menu {
  override def ingredients: List[Ingredient] = List(Milk,Sugar,Water)

  override def prize: Double = 4

  override def abbreviation: String = "chai"
}

case object BananaSmoothie extends Menu {
  override def ingredients: List[Ingredient] = List(Milk, Sugar, Water)

  override def prize: Double = 6

  override def abbreviation: String = "bananasmoothie"
}

case object StrawberryShake extends Menu {
  override def ingredients: List[Ingredient] = List(Milk, Sugar, Water)

  override def prize: Double = 7

  override def abbreviation: String = "strawberryshake"
}
case object Mojito extends Menu {
  override def ingredients: List[Ingredient] = List(Sugar, Water, Soda, Mint)

  override def prize: Double = 7.5

  override def abbreviation: String = "mojito"
}

object Menu  {

  private def menuMap = Map(
    Coffee.abbreviation.toLowerCase -> Coffee,
    Chai.abbreviation.toLowerCase -> Chai,
    BananaSmoothie.abbreviation.toLowerCase -> BananaSmoothie,
    StrawberryShake.abbreviation.toLowerCase -> StrawberryShake,
    Mojito.abbreviation.toLowerCase -> Mojito,
  )

  def apply(menu: String) = {
    if (validMenu(menu)) Right(menuMap(menu.toLowerCase))
    else  Left(InvalidMenu)
  }

  private def validMenu(menu: String) = menu.nonEmpty && menuMap.keySet.map(_.toLowerCase).contains(menu.toLowerCase)


}
