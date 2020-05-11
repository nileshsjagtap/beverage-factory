package domain

trait Error

case object EmptyMenuItem extends Error

case object InvalidMenu extends Error

case object InvalidIngredient extends Error

case object OrderContainsAllIngredients extends Error

case object EmptyOrder extends Error
