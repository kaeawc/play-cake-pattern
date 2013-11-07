package test.repositories

import test._
import play.api.test.Helpers._
import org.specs2._
import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import scala.concurrent.Await
import scala.concurrent.duration._

class UserRepositorySpec
  extends Specification
  with components.Default
  with Mockito {

  "UserRepository.getById" should {
    "return some user if it exists" in new WithApp {

        val result = users.getById(1L)

        Await.result(result, Duration(5, MILLISECONDS)) must beSome

    }
  }

  "UserRepository.findByName" should {
    "return a list of users" in new WithApp {

        val timeout = Duration(15, MILLISECONDS)

        Await.result(users.findByName("a"), timeout).length must between(6,7)
        Await.result(users.findByName("b"), timeout).length must between(1,1)
        Await.result(users.findByName("c"), timeout).length must between(2,2)
        Await.result(users.findByName("d"), timeout).length must between(2,3)
        Await.result(users.findByName("e"), timeout).length must between(5,5)
        Await.result(users.findByName("f"), timeout).length must between(1,2)
        Await.result(users.findByName("g"), timeout).length must between(1,1)
        Await.result(users.findByName("h"), timeout).length must between(1,1)

    }
  }

  "UserRepository.getByEmail" should {
    "return a user when there is an exact match" in new WithApp {

        val result = users.getByEmail("ben@company.com")

        Await.result(result, Duration(5, MILLISECONDS)) must beSome

    }
  }

  "UserRepository.create" should {
    "create a new user" in new WithApp {

        val request = users.create("zed","zed@company.com","zed's Ultimate password!")

        val result = Await.result(request, Duration(10, MILLISECONDS))

        result must beSome

        result.get must beGreaterThan(0L)

    }
  }

  "UserRepository.expire" should {
    "expire the given user" in new WithApp {

        val result = users.expire(1L)

        Await.result(result, Duration(5, MILLISECONDS)) must equalTo(1L)
    }
  }
}
