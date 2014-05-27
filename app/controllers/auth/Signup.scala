package controllers.auth

import entities.User
import play.api.data._
import play.api.mvc.{Controller, Action}
import play.api.libs.json._
import scala.concurrent.{Future, ExecutionContext}
import ExecutionContext.Implicits.global
import specs2.text

object Signup extends Controller with services.Auth {

  def auth = new AuthService

  def post = Action.async {
    implicit request =>

    forms.Signup.getForm.bindFromRequest match {
      case form:Form[SignupForm] if form.hasErrors =>
        Future { BadRequest(Json.obj("message" -> "Failed to parse the form.")) }
      case form:Form[SignupForm] =>
        auth.signup(form.get).map {
          case Some(created:User) => Created(Json.obj("created" -> created))
          case _ => InternalServerError(Json.obj("message" -> "Failed to create user account."))
        }
    }
  }
}
