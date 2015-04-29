package models.databaseComponent.pageComponent

import models.coreComponent.pageComponent.MenuItem
import models.databaseComponent.DBTrait
import scala.slick.driver.H2Driver.simple._

/**
 * Created by andre on 29.04.15.
 */
class DBMenuItem(tag:Tag) extends Table[MenuItem](tag,"MENU_ITEM") {

  def id = column[Long]("id",O.AutoInc,O.PrimaryKey)
  def title = column[String]("title")
  def fkTargetId = column[Long]("fk_target_id")
  def altTitle = column[String]("alt_title")
  def fkMenuId = column[Long]("fk_menu_id")
  def sortOder = column[Int]("sort_order")

  def fk_targetId = foreignKey("fk_menu_item_to_target_id",fkTargetId,DBPage)(_.id,ForeignKeyAction.Cascade, ForeignKeyAction.Cascade)
  def fk_menuId = foreignKey("fk_menu_item_to_menu_id",fkMenuId,DBMenu)(_.id,ForeignKeyAction.Cascade,ForeignKeyAction.Cascade)

  def * = (id,title,fkTargetId,altTitle,fkMenuId,sortOder).<>[MenuItem,(Long,String,Long,String,Long,Int)](
  {e => MenuItem( e._1,e._2,DBPage.findById(e._3).get,e._4,e._5,e._6 ) },
  {e => Some((e.id,e.title,e.target.id,e.altTitle,e.fkMenuId,e.sortOrder)) }
  )

}

object DBMenuItem extends TableQuery(new DBMenuItem(_)) with DBTrait {

  def findAllByMenuId(id:Long): Seq[MenuItem] = db.withSession(implicit ses => filter(_.fkMenuId === id).sortBy(_.sortOder asc).list )

  def findById(id:Long) = db.withSession(implicit ses => filter(_.id === id).list.headOption )

  def :+= (x: MenuItem) = db.withSession{implicit ses =>
    if(x.id < 1){
      (this returning map(_.id) into((r,id) => r.copy(id=id)) ) += x
    } else {
      filter(_.id === x.id).update(x)
      x
    }
  }

  def :-= (x: MenuItem) = db.withSession(implicit ses => filter(_.id === x.id) delete )

}
