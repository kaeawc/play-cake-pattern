package controllers.auth

import entities.User
import play.api.data._
import play.api.mvc.{Controller, Action}
import play.api.libs.json._
import scala.concurrent.{Future, ExecutionContext}
import ExecutionContext.Implicits.global
import specs2.text

object Login extends Controller with services.Auth {

  val auth = new AuthService

  def post = Action.async {
    implicit request =>

    forms.Login.getForm.bindFromRequest match {
      case form:Form[LoginForm] if form.hasErrors =>
        Future { BadRequest(Json.obj("message" -> "Failed to parse the form.")) }
      case form:Form[LoginForm] =>
        auth.login(form.get).map {
          case Some(created:User) => Created(Json.obj("created" -> created))
          case _ => Unauthorized(Json.obj("message" -> "Invalid credentials."))
        }
    }
  }
}
