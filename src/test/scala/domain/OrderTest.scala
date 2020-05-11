package domain

import org.specs2.mutable.Specification

class OrderTest extends Specification {

  "Order" should {

    "create order if Menu and Ingredients are valid" in {
      val expected: List[Order] = List(new Order(Chai, List(Sugar, Milk)))
      val response = Order.apply(Array("Chai, -sugar, -milk"))
      response must beRight(expected)
    }

    "create order if exclusion Ingredients are not available" in {
      val expected: List[Order] = List(new Order(Chai))
      val response = Order.apply(Array("Chai"))
      response must beRight(expected)
    }

    "not create order if Menu is empty" in {
      val expected: Error = InvalidMenu
      val response = Order.apply(Array(""))
      response must beLeft(expected)
    }

    "not create order if Menu are not valid" in {
      val expected: Error = InvalidMenu
      val response = Order.apply(Array("Pizza, -sugar, -milk"))
      response must beLeft(expected)
    }

    "not create order if Ingredients are not valid" in {
      val expected: Error = InvalidIngredient
      val response = Order.apply(Array("Chai, -lemon, -milk"))
      response must beLeft(expected)
    }

    "not create order if both Menu and Ingredients are not valid" in {
      val expected: Error = InvalidMenu
      val response = Order.apply(Array("Pizza, -lemon, -milk"))
      response must beLeft(expected)
    }

    "not create order if only Ingredients are available" in {
      val expected: Error = InvalidMenu
      val response = Order.apply(Array("-lemon, -milk"))
      response must beLeft(expected)
    }

  }

}
