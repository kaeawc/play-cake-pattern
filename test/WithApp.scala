package test

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.Logger
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._
import scala.concurrent.{ExecutionContext}
import ExecutionContext.Implicits.global

import org.specs2.execute.{Result, AsResult}

object Quick {

  def app = FakeApplication(additionalConfiguration =
    Map(
      "db.default.driver" -> "org.h2.Driver",
      "db.default.url" -> "jdbc:h2:mem:play;MODE=MYSQL;DB_CLOSE_DELAY=-1",
      "evolutionplugin" -> "enabled",
      "applyEvolutions.default" -> "true"
    )
  )

}

abstract class WithApp(app:FakeApplication = Quick.app) extends WithApplication(app) {
  override def around[T: AsResult](t: => T): Result = super.around {
    setupData()
    t
  }

  def setupData() {
    // setup data
  }
}

