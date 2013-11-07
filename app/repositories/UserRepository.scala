package repositories

import entities._
import scala.language.postfixOps
import scala.concurrent.{Future,ExecutionContext}
import ExecutionContext.Implicits.global

import anorm.SQL
import play.api.db.DB
import play.api.Play.current

trait UserRepositoryComponent {

  val userRepository:UserRepository

  trait UserRepository {

    def getById(id:Long):Future[Option[User]]

    def findByName(name:String):Future[List[User]]

    def getByEmail(email:String):Future[Option[User]]

    def create(name:String,email:String,password:String):Future[Option[Long]]

    def expire(id:Long):Future[Long]

    def findByAge(age:Int):Future[List[User]]

  }

  class AnormUsers extends UserRepository {

    def getById(id:Long) = Future {
      DB.withConnection { implicit connection =>
        SQL(
          """
            SELECT
              id,
              name,
              email,
              password,
              salt,
              active,
              age
            FROM user
            WHERE id = {id}
            LIMIT 1
          """
        ).on(
          'id -> id
        ).as(User.fromDB.singleOpt)
      }
    }

    def findByName(name:String) = Future {
      DB.withConnection { implicit connection =>
        SQL(
          """
            SELECT
              id,
              name,
              email,
              password,
              salt,
              active,
              age
            FROM user
            WHERE name LIKE '%' || {name} || '%'
          """
        ).on(
          'name -> name
        ).as(User.fromDB *)
      }
    }

    def findByAge(age:Int) = Future {
      DB.withConnection { implicit connection =>
      SQL(
        """
          SELECT
            id,
            name,
            email,
            password,
            salt,
            active,
            age
          FROM user
          WHERE age={age}
        """).on(
      'age -> age
      ).as(User.fromDB *)}
    }

    def getByEmail(email:String) = Future {
      DB.withConnection { implicit connection =>
        SQL(
          """
            SELECT
              id,
              name,
              email,
              password,
              salt,
              active,
              age
            FROM user
            WHERE email = {email}
            LIMIT 1
          """
        ).on(
          'email -> email
        ).as(User.fromDB.singleOpt)
      }
    }

    def create(name:String,email:String,password:String) = Future {
      val salt = "somesalt"
      DB.withConnection { implicit connection =>
        SQL(
          """
            INSERT INTO user (
              name,
              email,
              password,
              salt
            ) VALUES (
              {name},
              {email},
              {password},
              {salt}
            );
          """).on(
          'name -> name,
          'email -> email,
          'password -> password,
          'salt -> salt
        ).executeInsert()
      }
    }

    def expire(id:Long) = Future {
      DB.withConnection { implicit connection =>
        SQL(
          """
            UPDATE user
            SET active = 0
            WHERE id = {id}
          """
        ).on(
          'id -> id
        ).executeUpdate()
      }
    }
  }
}
