package forms

import play.api.data._
import play.api.data.Forms._

case class Signup(
  name:String,
  email:String,
  password:String
)

object Signup {

  def getForm = Form(
    mapping(
      "name" -> play.api.data.Forms.text,
      "email" -> play.api.data.Forms.email,
      "password" -> play.api.data.Forms.text
    )(forms.Signup.apply)(forms.Signup.unapply)
  )
}
