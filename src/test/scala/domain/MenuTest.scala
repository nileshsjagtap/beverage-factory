package domain

import org.specs2.mutable.Specification

class MenuTest extends Specification {

  "Menu " should {

  "return Menu if input array string is valid" in {
    val expected : Menu= Coffee
    val res = Menu.apply("Coffee")
    res must beRight(expected)
  }

  "return InvalidMenu if input array string is empty" in {
    val expected :Error= InvalidMenu
    val res = Menu.apply("")
    res must beLeft(expected)
  }

  "return InvalidMenu if input array string is not valid" in {
    val expected :Error= InvalidMenu
    val res = Menu.apply("mint")
    res must beLeft(expected)
  }
}

}
