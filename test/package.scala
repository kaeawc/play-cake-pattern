import org.specs2.mutable._
import play.api.test._
import play.api.mvc._
import play.api.libs.ws._

package object test extends Specification {

  def inMemory = FakeApplication(additionalConfiguration =
    Map(
      "db.default.driver" -> "org.h2.Driver",
      "db.default.url" -> "jdbc:h2:mem:play;MODE=MYSQL;DB_CLOSE_DELAY=-1",
      "evolutionplugin" -> "enabled",
      "applyEvolutions.default" -> "true"
    )
  )

}
