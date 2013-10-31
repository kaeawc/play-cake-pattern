package test.repositories

import test._

import play.api.test.Helpers._

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

import scala.concurrent.Await
import scala.concurrent.duration._

class UserRepositorySpec
  extends Specification
  with components.Default
  with Mockito {

  "UserRepository.getById" should {
    "return some user if it exists" in {
      running(inMemory) {

        val result = userRepository.getById(1L)

        Await.result(result, Duration(500, MILLISECONDS)) must beSome

      }
    }
  }

  "UserRepository.findByName" should {
    "return a list of users" in {
      running(inMemory) {

        val wait = Duration(500, MILLISECONDS)

        Await.result(userRepository.findByName("a"), wait).length must between(6,7)
        Await.result(userRepository.findByName("b"), wait).length must between(1,1)
        Await.result(userRepository.findByName("c"), wait).length must between(2,2)
        Await.result(userRepository.findByName("d"), wait).length must between(2,3)
        Await.result(userRepository.findByName("e"), wait).length must between(5,5)
        Await.result(userRepository.findByName("f"), wait).length must between(1,2)
        Await.result(userRepository.findByName("g"), wait).length must between(1,1)
        Await.result(userRepository.findByName("h"), wait).length must between(1,2)

      }
    }
  }

  "UserRepository.getByEmail" should {
    "return a user when there is an exact match" in {
      running(inMemory) {

        val result = userRepository.getByEmail("ben@company.com")

        Await.result(result, Duration(500, MILLISECONDS)) must beSome

      }
    }
  }

  "UserRepository.findByAge" should {
    "return a list of users with the provided age" in {
      running(inMemory){
        val wait = Duration(500, MILLISECONDS)
        Await.result(userRepository.findByAge(26), wait).length mustEqual(1)
        Await.result(userRepository.findByAge(52), wait).length mustEqual(1)
        Await.result(userRepository.findByAge(0), wait).length mustEqual(9)
      }
    }
  }

  "UserRepository.create" should {
    "create a new user" in {
      running(inMemory) {

        val request = userRepository.create("zed","zed@company.com","zed's Ultimate password!")

        val result = Await.result(request, Duration(500, MILLISECONDS))

        result must beSome

        result.get must beGreaterThan(0L)

      }
    }
  }

  "UserRepository.expire" should {
    "expire the given user" in {
      running(inMemory) {

        val result = userRepository.expire(1L)

        Await.result(result, Duration(500, MILLISECONDS)) must equalTo(1L)
      }
    }
  }
}
