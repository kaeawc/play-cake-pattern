package services

import repositories._

trait UserServiceComponent {
  this: UserRepositoryComponent =>

  val users:UserService

  class UserService {

    def getById(id:Long) = userRepository.getById(id)

    def findByName(name:String) = userRepository.findByName(name)

    def getByEmail(email:String) = userRepository.getByEmail(email)

    def create(name:String,email:String,password:String) = userRepository.create(name,email,password)

    def expire(id:Long) = userRepository.expire(id)

    def findByAge(age:Int) = userRepository.findByAge(age)

  }
}
