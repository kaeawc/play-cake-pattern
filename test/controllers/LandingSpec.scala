package controllers

import play.api.test._
import play.api.test.Helpers._

class LandingSpec extends Specification {

  "GET /" should {

    "return 200 always" in new App {

        val header = FakeRequest(GET, "/")

        val result = route(header).get

        status(result) must equalTo(OK)

    }
  }

  "GET anything else not in the routes file" should {

    "return 404 always" in new App {

        val header = FakeRequest(GET, "/anythingelse")

        val result = route(header).get

        status(result) must equalTo(404)

    }
  }
}
