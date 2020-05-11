package app

import domain.{BillGenerator, Order}

object Run extends App{

  val billForOrder = {
    for {
      orders <- Order(Array("Chai, -sugar, -milk"))
      bill <-  BillGenerator.calculateBill(orders)
    } yield bill
  }


  billForOrder.fold(err => println(err), bill => println(s"bill for order is ${bill}"))
}
