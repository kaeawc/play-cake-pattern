package repositories

import play.api.test._
import play.api.test.Helpers._

class UsersSpec
  extends Specification
  with Users
  with org.specs2.mock.Mockito {

  val users = new AnormUsers

  "UserRepository.getById" should {
    "return some user if it exists" in new App {

        val user = waitFor { users.getById(1L) }
        user must beSome

    }
  }

  "UserRepository.findByName" should {
    "return a list of users" in new App {

        waitFor { users.findByName("a") }.length must between(6,7)
        waitFor { users.findByName("b") }.length must between(1,1)
        waitFor { users.findByName("c") }.length must between(2,2)
        waitFor { users.findByName("d") }.length must between(2,3)
        waitFor { users.findByName("e") }.length must between(5,5)
        waitFor { users.findByName("f") }.length must between(1,2)
        waitFor { users.findByName("g") }.length must between(1,1)
        waitFor { users.findByName("h") }.length must between(1,2)

    }
  }

  "UserRepository.getByEmail" should {
    "return a user when there is an exact match" in new App {

        waitFor { users.getByEmail("ben@company.com") } must beSome

    }
  }

  "UserRepository.create" should {
    "create a new user" in new App {

        val result = waitFor { users.create("zed","zed@company.com","zed's Ultimate password!") }

        result must beSome

        val user = result.get

        user.id must beGreaterThan(0L)

    }
  }

  "UserRepository.expire" should {
    "expire the given user" in new App {

        waitFor { users.expire(1L) } must equalTo(1L)
    }
  }
}
