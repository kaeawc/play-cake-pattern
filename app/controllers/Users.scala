package controllers

import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.{Future, ExecutionContext}
import ExecutionContext.Implicits.global
import specs2.text

object Users extends Application {

  def getById(id:Long) = Action.async {
    users.getById(id).map {
      result =>
      result.map {
        user =>
        Ok(user.toJson)
      }.getOrElse {
        NotFound(Json.obj("message" -> "Could not find the requested user."))
      }
    }
  }

  def findByName(name:String) = Action.async {
    users.findByName(name).map {
      list =>
      Ok(Json.toJson(list))
    }
  }

  def getByEmail(email:String) = Action.async {
    users.getByEmail(email).map {
      result =>
      result.map {
        user =>
        Ok(user.toJson)
      }.getOrElse {
        NotFound(Json.obj("message" -> "Could not find the requested user."))
      }
    }
  }

  def findByAge(age: Int) = Action.async {
    users.findByAge(age).map {
      list =>
        Ok(Json.toJson(list))
    }
  }

  import play.api.data._
  import play.api.data.Forms._

  def create = Action.async {
    implicit request =>

    val form = Form(
      tuple(
        "name" -> play.api.data.Forms.text,
        "email" -> play.api.data.Forms.email,
        "password" -> play.api.data.Forms.text
      )
    ).bindFromRequest()

    if(form.hasErrors) {
      Future { BadRequest(Json.obj("message" -> "Failed to parse the form.")) }
    } else {

      val (name,email,password) = form.get

      println(name,email,password)

      users.create(name,email,password).map {
        created => Created(Json.obj("created" -> created))
      }
    }
  }

  def expire(id:Long) = Action.async {
    users.expire(id).map {
      expired =>

      if(expired > 0)
        Accepted(Json.obj("expired" -> expired))
      else
        NotFound(Json.obj("message" -> "Could not find the requested user."))
    }
  }

}
