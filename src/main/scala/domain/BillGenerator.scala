package domain

object BillGenerator {

  def calculateBill(orders: List[Order]): Either[Error, Double] = {
    if(orders.isEmpty)
      Left(EmptyOrder)
    else
      Right(orders.foldLeft(0.0)((acc, order) => acc + order.menu.prize - order.exclusionIngredient.map(a => a.prize).sum))
  }

}
