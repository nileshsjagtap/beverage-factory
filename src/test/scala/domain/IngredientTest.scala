package domain

import org.specs2.mutable.Specification

class IngredientTest extends Specification {

  "Ingredients " should {

    "return ingredient if input array string is valid" in {
      val expected = List(Milk, Mint)
      val res = Ingredient.apply(Array("milk", "mint"))
      res must beRight(expected)
    }

    "return InvalidIngredient if input array string is empty" in {
      val expected :Error= InvalidIngredient
      val res = Ingredient.apply(Array(""))
      res must beLeft(expected)
    }

    "return InvalidIngredient if input array string is not valid" in {
      val expected :Error= InvalidIngredient
      val res = Ingredient.apply(Array("Coffee", "mint"))
      res must beLeft(expected)
    }
  }

}
