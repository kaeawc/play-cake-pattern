package services

import play.api.test._
import scala.concurrent.{Future,ExecutionContext,Await}
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import org.specs2.mock.Mockito

class AuthSpec
  extends Specification
  with Auth
  with Mockito {

  val mockUser = mock[entities.User]
  mockUser.password.returns("asdfasdf")
  val auth = spy(new AuthService)
  val mockedDatabase = mock[auth.AnormUsers]
  org.mockito.Mockito.doReturn(mockedDatabase).when(auth).users

  val signupForm = forms.Signup("Some One","some.one@example.com","asdfasdf")
  val loginForm = forms.Login("some.one@example.com","asdfasdf")

  "AuthService.signup" should {
    "return some user if it exists" in {

      mockedDatabase.create(signupForm.name, signupForm.email, signupForm.password).returns(Future { Some(mockUser) })

      val result = waitFor { auth.signup(signupForm) }

      result must beSome(mockUser)

    }
  }

  "AuthService.signup" should {
    "return some user if it exists" in {

      mockedDatabase.getByEmail(loginForm.email).returns(Future { Some(mockUser) })
      mockUser.password mustEqual "asdfasdf"

      val result = waitFor { auth.login(loginForm) }

      result must beSome(mockUser)

    }
  }
}
