package models.databaseComponent

import com.mchange.v2.c3p0.ComboPooledDataSource
import models.databaseComponent.pageComponent._
import scala.slick.driver.H2Driver.simple._
import scala.util.Try

/**
 * Created by andre on 29.04.15.
 */
object H2Database {

  private val driver = "org.h2.Driver"/*"com.mysql.jdbc.Driver"*/ //Play.current.configuration.getString("db.default.driver").getOrElse("")
  private val user = "c1TEST"//Play.current.configuration.getString("db.default.user").getOrElse("")
  private val url = "jdbc:h2:/Development/PlayCMS/db"/*"jdbc:mysql://46.4.103.179:3306/c1slickTest"*/ //Play.current.configuration.getString("db.default.url").getOrElse("")
  private val pass = "123456789"//Play.current.configuration.getString("db.default.password").getOrElse("")

  private[databaseComponent] val db = {
    val ds = new ComboPooledDataSource
    ds.setDriverClass(driver)
    ds.setUser(user)
    ds.setPassword(pass)
    ds.setJdbcUrl(url)
    ds.setMaxIdleTime(300)
    ds.setLoginTimeout(300)
    ds.setMinPoolSize(8)
    ds.setInitialPoolSize(10)
    Database.forDataSource(ds)
  }

  private lazy val dbList = Seq[TableQuery[_ <: Table[_]]](
    DBHTMLSupervisor,
    DBPage,
    DBMenu,
    DBMenuItem
  )

  def initDatabase(): Unit = {
    db.withSession{implicit ses =>
      dbList.foreach( e => Try(e.ddl.create) )
    }
  }

}
