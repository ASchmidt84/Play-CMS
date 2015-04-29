package models.databaseComponent.pageComponent

import models.coreComponent.pageComponent.Position.Position
import models.coreComponent.pageComponent.{Position, Menu}
import models.databaseComponent.DBTrait
import scala.slick.driver.H2Driver.simple._
/**
 * Created by andre on 29.04.15.
 */
class DBMenu(tag:Tag) extends Table[Menu](tag,"MENU"){

  def id = column[Long]("id",O.AutoInc,O.PrimaryKey)
  def position = column[Int]("position")
  def active = column[Boolean]("active")
  def fkHtmlSupervisorId = column[Long]("fk_html_supervisor_id",O.Nullable)
  def name = column[String]("name")

  def nameUnique = index("name_unique",name,unique = true)
  def fk_htmlSupervisorId = foreignKey("fk_menu_to_html_supervisor_id",fkHtmlSupervisorId,DBHTMLSupervisor)(_.id,ForeignKeyAction.Cascade,ForeignKeyAction.SetNull)

  def * = (id,position,active,fkHtmlSupervisorId.?,name).<>[Menu,(Long,Int,Boolean,Option[Long],String)](
  {e => Menu(e._1,Position(e._2),e._3,e._4.map(r => DBHTMLSupervisor.findById(r).get),e._5) },
  {e => Some((e.id,e.position.id,e.active,e.htmlSupervisor.map(_.id),e.name)) }
  )

}

object DBMenu extends TableQuery(new DBMenu(_)) with DBTrait {

  def findById(id:Long) = db.withSession(implicit ses => filter(_.id === id).list.headOption )

  def :+= (x: Menu) = db.withSession{implicit ses =>
    val modified = x.copy( htmlSupervisor = x.htmlSupervisor.map(r => DBHTMLSupervisor :+= r ) )
    val myId = if( modified.id < 1 ){
      (this returning map(_.id) ) += modified
    } else {
      filter(_.id === x.id).update(modified)
      modified.id
    }

    findById(myId).get
  }

  def :-= (x: Menu) = db.withSession(implicit ses => filter(_.id === x.id) delete )

  def findAllActiveByPosition(pos: Position) = db.withSession(implicit ses => filter(t => t.position === pos.id && t.active === true).list )

}