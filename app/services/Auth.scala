package services

import entities.User
import scala.concurrent.{Future,ExecutionContext}
import ExecutionContext.Implicits.global

trait Auth {

  def auth:AuthContract
  type SignupForm = forms.Signup
  type LoginForm = forms.Login

  trait AuthContract extends repositories.Users {

    def login(form:LoginForm):Future[Option[User]]

    def signup(form:SignupForm):Future[Option[User]]

  }

  class AuthService extends AuthContract {

    def users = new AnormUsers

    def login(form:LoginForm) =
      users.getByEmail(form.email) map {
        case Some(user:User) => {

          if (user.password == form.password)
            Some(user)
          else
            None
        }
        case _ => None
      }

    def signup(form:SignupForm) =
      users.create(form.name,form.email,form.password)

  }
}
