package components

import repositories._
import services._

trait Default
  extends UserServiceComponent
  with UserRepositoryComponent
{
  val userRepository:UserRepository = new AnormUsers
  val users = new UserService
}
