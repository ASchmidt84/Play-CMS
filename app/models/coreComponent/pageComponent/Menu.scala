package models.coreComponent.pageComponent

import models.coreComponent.pageComponent.Position.Position
import models.databaseComponent.pageComponent.DBMenu

/**
 * Created by andre on 29.04.15.
 */
case class Menu(id:Long,
                position: Position,
                active: Boolean,
                htmlSupervisor: Option[HTMLSupervisor],
                name: String) {

  lazy val menuItemSeq = MenuItem.allByMenuId(id)

}


object Menu {

  def seqByPosition(pos: Position) = DBMenu findAllActiveByPosition pos

  def safe(menu:Menu) = DBMenu :+= menu

  def delete(menu: Menu) = DBMenu :-= menu

}