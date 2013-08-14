package test.routes


import org.specs2.mutable._
import org.specs2.mock.Mockito

import test._
import play.api.libs.json._
import play.api.test._
import play.api.test.Helpers._

class UserRouteSpec
  extends Specification
  with Mockito {

  "GET /user/:id" should {

    "bad request when given a String" in {
      running(inMemory) {

        val header = FakeRequest(GET, "/user/asdfasdf")

        val result = route(header).get

        status(result) must equalTo(400)

      }
    }

    "bad request when given a fraction" in {
      running(inMemory) {

        val header = FakeRequest(GET, "/user/123.51")

        val result = route(header).get

        status(result) must equalTo(400)

      }
    }

    "not found if the user doesn't exist" in {
      running(inMemory) {

        val header = FakeRequest(GET, "/user/9999999")

        val result = route(header).get

        status(result) must equalTo(404)

      }
    }

    "found if the user exists" in {
      running(inMemory) {

        val header = FakeRequest(GET, "/user/1")

        val result = route(header).get

        status(result) must equalTo(200)

      }
    }
  }

  "GET /users/by/name/:name" should {
    "return a list of users" in {
      running(inMemory) {

        val header = FakeRequest(GET, "/users/by/name/Alice")

        val result = route(header).get

        status(result) must equalTo(200)
      }
    }
  }

  "GET /user/by/email/:email" should {
    "return a user when there is an exact match" in {
      running(inMemory) {

        val header = FakeRequest(GET, "/user/by/email/alice@company.com")

        val result = route(header).get

        status(result) must equalTo(200)
      }
    }
  }

  "POST /create/user" should {
    "create a new user" in {
      running(inMemory) {

        val anyEmail =  "asdf@company.com"

        val header = FakeRequest(POST, "/user")
        val body = Json.obj(
          "name" -> "asdf fdas",
          "email" -> anyEmail,
          "password" -> "password"
        )

        val result = route(header,body).get

        status(result) must equalTo(201)
      }
    }

    "fail if there is no request body" in {
      running(inMemory) {

        val header = FakeRequest(POST, "/user")

        val result = route(header).get

        status(result) must equalTo(400)
      }
    }

    "fail if the request body can't be bound correctly" in {
      running(inMemory) {

        val header = FakeRequest(POST, "/user")

        val result = route(header).get

        status(result) must equalTo(400)
      }
    }
  }

  "DELETE /user/:id" should {

    "bad request when given a String" in {
      running(inMemory) {

        val header = FakeRequest(DELETE, "/user/asdfasdf")

        val result = route(header).get

        status(result) must equalTo(400)

      }
    }

    "bad request when given a fraction" in {
      running(inMemory) {

        val header = FakeRequest(DELETE, "/user/123.51")

        val result = route(header).get

        status(result) must equalTo(400)

      }
    }

    "not found if the user doesn't exist" in {
      running(inMemory) {

        val header = FakeRequest(DELETE, "/user/9999999")

        val result = route(header).get

        status(result) must equalTo(404)

      }
    }

    "expire the given user" in {
      running(inMemory) {

        val header = FakeRequest(DELETE, "/user/1")

        val result = route(header).get

        status(result) must equalTo(202)
      }
    }
  }
}
