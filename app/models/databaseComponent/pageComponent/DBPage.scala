package models.databaseComponent.pageComponent

import models.coreComponent.pageComponent.Page
import models.databaseComponent.DBTrait
import scala.slick.driver.H2Driver.simple._

/**
 * Created by andre on 29.04.15.
 */
class DBPage(tag: Tag) extends Table[Page](tag,"PAGE") {

  def id = column[Long]("id",O.PrimaryKey,O.AutoInc)
  def urlTitle = column[String]("url_title")
  def seoTitle = column[String]("seo_title")
  def seoMetaTags = column[String]("seo_meta_tags")
  def seoDescription = column[String]("seo_description")
  def title = column[String]("title")
  def content = column[String]("content")
  def active = column[Boolean]("active")
  def additionalHeaderHtml = column[String]("additional_header_html")

  def * = (id,urlTitle,seoTitle,seoMetaTags,seoDescription,title,content,active,additionalHeaderHtml) <> ( (Page.apply _ ).tupled, Page.unapply )

}


object DBPage extends TableQuery(new DBPage(_)) with DBTrait {

  def :+= (x: Page) = db.withSession{implicit ses =>
    if(x.id < 1){
      (this returning map(_.id) into( (r,id) => r.copy(id=id) )) += x
    } else {
      filter(_.id === x.id).update(x)
      x
    }
  }

  def :-= (x: Page) = db.withSession(implicit ses => filter(_.id === x.id).delete )

  def findById(id:Long) = db.withSession(implicit ses => filter(_.id === id).list.headOption )

  def findByUrlTitle(url:String) = db.withSession(implicit ses => filter(_.urlTitle.toLowerCase === url.toLowerCase).list.headOption )

}