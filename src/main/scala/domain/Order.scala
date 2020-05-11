package domain

case class Order(menu: Menu, exclusionIngredient: List[Ingredient] = Nil)

object Order {

  def apply(menuItems: Array[String]): Either[Error, List[Order]] = {
    if(menuItems.nonEmpty)
      sequence(menuItems.toList.map(item => createOrder(item)))
    else
      Left(EmptyMenuItem)
  }

  def createOrder(menuItem: String) = {
    for {
      menu <- createMenu(menuItem).right
      ingredientForExclusion <- createIngredient(menuItem)
    } yield Order(menu, ingredientForExclusion)
  }

  def createMenu(menuItem: String): Either[InvalidMenu.type, Menu] = Menu(menuItem.split(", -")(0))

  def createIngredient(menuItem: String) = {
    val menuAndExclusion = menuItem.split(", -")
    if(menuAndExclusion.size > 1) Ingredient(menuAndExclusion.drop(1)) else Right(Nil)
  }

  def checkForAllExclusiveIngredients(menu: Menu, ingredient: List[Ingredient]) =
    menu.ingredients.forall(ingredient.contains(_))

  private def sequence[L, R](listOfEither: List[Either[L, R]]): Either[L, List[R]] = {
    def iterate(remaining: List[Either[L, R]], buffer: Either[L, List[R]]): Either[L, List[R]] = remaining match {
      case Nil => buffer
      case head :: _ if head.isLeft => Left(head.left.get)
      case head :: tail => iterate(tail, Right(buffer.right.get :+ head.right.get))
    }
    iterate(listOfEither, Right(List[R]()))
  }
  
}
