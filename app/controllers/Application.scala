package controllers

import models.coreComponent.pageComponent.Page
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object Application extends Controller {

  def index = Action{implicit req =>
    Ok(views.html.frontend.page.webPage(Page.index))
  }

  def page(page:Page) = Action{implicit req =>
    Ok( views.html.frontend.page.webPage(page) )
  }


}