package play.api.test

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

abstract class App(app:FakeApplication = App.app) extends WithApplication(app) {

  override def around[T: AsResult](t: => T): Result = super.around {
    setupData()
    t
  }

  def setupData() {

  }
}

object App {

  def app = FakeApplication(additionalConfiguration =
    Map(
      "evolutionplugin" -> "enabled",

      "db.default.driver" -> "org.h2.Driver",
      "db.default.url" -> "jdbc:h2:mem:default",
      "applyEvolutions.default" -> "true",
      "applyDownEvolutions.default" -> "true"
    )
  )
}
