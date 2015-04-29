package models.databaseComponent.pageComponent

import models.coreComponent.pageComponent.HTMLSupervisor
import models.databaseComponent.DBTrait
import scala.slick.driver.H2Driver.simple._

/**
 * Created by andre on 29.04.15.
 */
class DBHTMLSupervisor(tag:Tag) extends Table[HTMLSupervisor](tag,"HTML_SUPERVISOR") {

  def id = column[Long]("id",O.AutoInc,O.PrimaryKey)
  def htmlClass = column[String]("html_class")
  def htmlId = column[String]("html_id")

  def * = (id,htmlClass,htmlId).<>[HTMLSupervisor,(Long,String,String)](
  {e => HTMLSupervisor(e._1,e._2,e._3) },
  {e => Some( (e.id,e.htmlClass,e.htmlId) ) }
  )

}

object DBHTMLSupervisor extends TableQuery(new DBHTMLSupervisor(_)) with DBTrait {

  def :+= (x: HTMLSupervisor) = db.withSession{implicit ses =>
    if(x.id < 1){
      (this returning map(_.id) into((r,id) => r.copy(id=id)) ) += x
    } else {
      filter(_.id === x.id).update(x)
      x
    }
  }

  def :-= (x: HTMLSupervisor) = db.withSession(implicit ses => filter(_.id === x.id).delete )

  def findById(id:Long) = db.withSession(implicit ses => filter(_.id === id).list.headOption )

}