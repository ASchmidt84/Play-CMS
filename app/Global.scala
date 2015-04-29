import models.databaseComponent.H2Database
import play.api._

/**
 * Created by andre on 29.04.15.
 */
object Global extends GlobalSettings {

  override def onStart(app:Application): Unit = {
    H2Database.initDatabase() //Setup of
  }

}
