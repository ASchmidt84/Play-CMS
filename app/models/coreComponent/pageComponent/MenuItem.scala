package models.coreComponent.pageComponent

import models.databaseComponent.pageComponent.DBMenuItem

/**
 * Created by andre on 29.04.15.
 */
case class MenuItem(id:Long,
                    title: String,
                    target: Page,
                    altTitle:String,
                    fkMenuId:Long,
                    sortOrder: Int) {

}

object MenuItem {

  def allByMenuId(id:Long) = DBMenuItem.findAllByMenuId(id)

  def safe(menuItem: MenuItem) = DBMenuItem :+= menuItem

  def delete(menuItem: MenuItem) = DBMenuItem :-= menuItem

}
