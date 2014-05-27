package repositories

import scala.Date._
import entities._
import scala.language.postfixOps
import scala.concurrent.{Future,ExecutionContext}
import ExecutionContext.Implicits.global

import anorm.SQL
import play.api.db.DB
import play.api.Play.current

trait Users {

  def users:UserContract

  trait UserContract {

    def getById(id:Long):Future[Option[User]]

    def findByName(name:String):Future[List[User]]

    def getByEmail(email:String):Future[Option[User]]

    def create(name:String,email:String,password:String):Future[Option[User]]

    def expire(id:Long):Future[Long]

  }

  class AnormUsers extends UserContract {

    val fields = """
      id,
      name,
      email,
      password,
      salt,
      active,
      created"""

    def getById(id:Long) = Future {
      DB.withConnection { implicit connection =>
        SQL(s"""
            SELECT $fields
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
        SQL(s"""
            SELECT $fields
            FROM user
            WHERE name LIKE '%' || {name} || '%'
          """
        ).on(
          'name -> name
        ).as(User.fromDB *)
      }
    }

    def getByEmail(email:String) = Future {
      DB.withConnection { implicit connection =>
        SQL(s"""
            SELECT $fields
            FROM user
            WHERE email = {email}
            LIMIT 1
          """
        ).on(
          'email -> email
        ).as(User.fromDB.singleOpt)
      }
    }

    def create(name:String,email:String,password:String) = {

      val created = now
      val active = true
      val salt = "somesalt"

      Future {
        DB.withConnection { implicit connection =>
          SQL(
            """
              INSERT INTO user (
                name,
                email,
                password,
                salt,
                active,
                created
              ) VALUES (
                {name},
                {email},
                {password},
                {salt},
                {active},
                {created}
              );
            """).on(
            'name ->     name,
            'email ->    email,
            'password -> password,
            'salt ->     salt,
            'active ->   active,
            'created ->  asDate(created)
          ).executeInsert()
        }
      } map {
        case Some(id:Long) =>
          Some(User(
            id,
            name,
            email,
            password,
            salt,
            active,
            created
          ))
        case _ => None
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
