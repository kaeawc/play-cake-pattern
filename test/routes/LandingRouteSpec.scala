package test.routes

import org.specs2.mutable._

import test._
import play.api.test._
import play.api.test.Helpers._

class LandingRouteSpec extends Specification {

  "GET /" should {

    "return 200 always" in new WithApp {

        val header = FakeRequest(GET, "/")

        val result = route(header).get

        status(result) must equalTo(OK)

    }
  }

  "GET anything else not in the routes file" should {

    "return 404 always" in new WithApp {

        val header = FakeRequest(GET, "/anythingelse")

        val result = route(header).get

        status(result) must equalTo(404)

    }
  }
}
