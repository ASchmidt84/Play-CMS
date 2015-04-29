package models.coreComponent.pageComponent

/**
 * Created by andre on 29.04.15.
 */
object Position extends Enumeration {
  type Position = Value

  val Top = Value(1,"top")
  val Right = Value(2,"right")
  val Bottom = Value(3,"bottom")
  val Center = Value(4,"center")
  val Left = Value(5,"left")

}
