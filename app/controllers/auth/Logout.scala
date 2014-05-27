package controllers.auth

import entities.User
import play.api.data._
import play.api.mvc.{Controller, Action}
import play.api.libs.json._
import scala.concurrent.{Future, ExecutionContext}
import ExecutionContext.Implicits.global
import specs2.text

object Logout extends Controller {

  def post = Action { Ok(Json.obj("message" -> "You are now logged out.")) }

}
