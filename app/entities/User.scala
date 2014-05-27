package entities

import anorm._
import anorm.SqlParser._

import play.api.libs.json._
import specs2.text
import scala.Date._

case class User(
  id       : Long,
  name     : String,
  email    : String,
  password : String,
  salt     : String,
  active   : Boolean = true,
  created  : DateTime
) {

  def toJson:JsValue = User.toJson(this)

}

object User extends ((
  Long,
  String,
  String,
  String,
  String,
  Boolean,
  DateTime
) => User) {

  implicit val jsonFormat = Json.format[User]

  def makeSalt = "salt"

  val sqlResult =
    get[Long]("id") ~
    get[String]("name") ~
    get[String]("email") ~
    get[String]("password") ~
    get[String]("salt") ~
    get[Boolean]("active") ~
    get[Date]("created")

  val fromDB = sqlResult map {
    case id~name~email~password~salt~active~created =>
    User(id,name,email,password,salt,active,asDateTime(created)) }

  def toJson(user:User):JsValue = Json.toJson(user)
  def fromJson(user:JsValue):Option[User] = {
    try {
      Option(Json.fromJson(user).get)
    } catch {
      case e:Exception => None
    }
  }
}
