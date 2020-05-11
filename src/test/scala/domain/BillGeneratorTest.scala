package domain

import org.specs2.mutable.Specification

class BillGeneratorTest extends Specification {

  "Bill gernarator" should {

    "calculate total for all Orders" in {
      val expected = 8.0
      val res = BillGenerator.calculateBill(List(Order(Chai), Order(Coffee, List(Milk))))
      res must beRight(expected)
    }

    "return an error if orders are empty" in {
      val expected: Error = EmptyOrder
      val res = BillGenerator.calculateBill(List())
      res must beLeft(expected)
    }
  }

}
