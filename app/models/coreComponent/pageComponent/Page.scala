package models.coreComponent.pageComponent

import models.databaseComponent.pageComponent.DBPage
import play.api.mvc.PathBindable

/**
 * Created by andre on 28.04.15.
 */
/**
 * This is a normal webpage with normal html Content
 * @param id unique identifier
 * @param urlTitle unique URL title for REST Request
 * @param seoTitle the seo title in header
 * @param seoMetaTags the seo meta tags in header
 * @param seoDescription the seo description in header
 * @param title title of the page not unique
 * @param content the HTML content of the website
 * @param active the state of the page
 * @param additionalHeaderHtml additional header information like JavaScript, Style ot anything else
 */
case class Page(id:Long,
                urlTitle:String,
                seoTitle:String,
                seoMetaTags: String,
                seoDescription:String,
                title:String,
                content:String,
                active: Boolean,
                additionalHeaderHtml: String) {

}

object Page {
  def index = DBPage findById 1l getOrElse Page(0l,"index","","","","Index","<h1>Guten Tag</h1><p>Ich bin leer</p>",true,"")
  def safe(x: Page) = DBPage :+= x
  def delete(x:Page): Unit = DBPage :-= x
  def findByUrl(url: String) = DBPage findByUrlTitle url

  /**
   * We create here a url string. We delete all whitespaces and non word-characters
   * @param input the string which needs to be made url conform
   * @return the url title string url conform
   */
  def makeURLTitle(input: String) = input.replaceAll("\\W","").replaceAll("\\s+"," ").replaceAll("\\s","-")

  /**
   * Implicit binder to get the page we are looking for
   * @param intBinder
   * @return
   */
  implicit def pathBinder(implicit intBinder: PathBindable[String]) = new PathBindable[Page]{
    override def bind(key:String,value:String): Either[String, Page] = {
      for{
        id <- intBinder.bind(key,value).right
        imageSlot <- findByUrl(id).toRight("Not Found").right
      } yield imageSlot
    }
    override def unbind(key:String,page: Page): String = {
      intBinder.unbind(key,page.urlTitle)
    }
  }

}