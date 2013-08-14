package entities

import anorm._
import anorm.SqlParser._

import play.api.libs.json._
import specs2.text

case class User(
  id       : Long,
  name     : String,
  email    : String,
  password : String,
  salt     : String,
  active   : Boolean = true
) {

  def toJson:JsValue = User.toJson(this)

}

object User extends ((
  Long,
  String,
  String,
  String,
  String,
  Boolean
) => User) {

  implicit val r = Json.reads[User]
  implicit val w = Json.writes[User]

  def makeSalt = "salt"

  val sqlResult =
    get[Long]("id") ~
    get[String]("name") ~
    get[String]("email") ~
    get[String]("password") ~
    get[String]("salt") ~
    get[Boolean]("active")

  val fromDB = sqlResult map {
    case id~name~email~password~salt~active =>
    User(id,name,email,password,salt,active) }

  def toJson(user:User):JsValue = Json.toJson(user)
  def fromJson(user:JsValue):Option[User] = {
    try {
      Option(Json.fromJson(user).get)
    } catch {
      case e:Exception => None
    }
  }
}
