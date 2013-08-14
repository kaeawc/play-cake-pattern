package test.services

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import scala.concurrent.{Future,ExecutionContext,Await}
import ExecutionContext.Implicits.global
import scala.concurrent.duration._

class UserServiceSpec
  extends Specification
  with components.Default
  with Mockito {

  val user = mock[entities.User]
  override val userRepository = mock[UserRepository]


  "UserService.getById" should {
    "return some user if it exists" in {

      userRepository.getById(anyLong).returns(Future { Some(user) })

      val result = users.getById(anyLong)

      Await.result(result, Duration(5, MILLISECONDS)) must beSome(user)

    }
  }

  "UserService.findByName" should {
    "return a list of users" in {

      userRepository.findByName(anyString).returns(Future { Nil })

      val results = users.findByName(anyString)

      Await.result(results, Duration(5, MILLISECONDS)).map {
        result =>
        result must equalTo(user)
      }

    }
  }

  "UserService.getByEmail" should {
    "return a user when there is an exact match" in {

      userRepository.getByEmail(anyString).returns(Future { Some(user) })

      val result = users.getByEmail(anyString)

      Await.result(result, Duration(5, MILLISECONDS)) must beSome(user)

    }
  }

  "UserService.create" should {
    "create a new user" in {

      var next = 0L

      userRepository.create(anyString,anyString,anyString).returns(Future { Some(next) })

      val result = users.create(anyString,anyString,anyString)

      Await.result(result, Duration(5, MILLISECONDS)) must beSome(next)

    }
  }

  "UserService.expire" should {
    "expire the given user" in {

      userRepository.expire(anyLong).returns(Future { anyLong })

      val result = users.expire(anyLong)

      Await.result(result, Duration(5, MILLISECONDS)) must equalTo(anyLong)
    }
  }
}
