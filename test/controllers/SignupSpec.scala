package controllers

import play.api.libs.json._
import play.api.test._
import play.api.test.Helpers._

class SignupSpec
  extends Specification {

  "POST /signup" should {
    "create a new user" in new App {

        val anyEmail =  "asdf@company.com"

        val header = FakeRequest(POST, "/signup")
        val body = Json.obj(
          "name" -> "asdf fdas",
          "email" -> anyEmail,
          "password" -> "password"
        )

        val result = route(header,body).get

        status(result) must equalTo(201)
    }

    "fail if there is no request body" in new App {

        val header = FakeRequest(POST, "/signup")

        val result = route(header).get

        status(result) must equalTo(400)
    }

    "fail if the request body can't be bound correctly" in new App {

        val header = FakeRequest(POST, "/signup")

        val result = route(header).get

        status(result) must equalTo(400)
    }
  }
}
