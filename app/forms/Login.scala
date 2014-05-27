package forms

import play.api.data._
import play.api.data.Forms._

case class Login(
  email:String,
  password:String
)

object Login {

  def getForm = Form(
    mapping(
      "email" -> play.api.data.Forms.email,
      "password" -> play.api.data.Forms.text
    )(forms.Login.apply)(forms.Login.unapply)
  )
}
