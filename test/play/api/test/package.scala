package play.api

import scala.concurrent._
import scala.concurrent.duration._

package object test {
  type Specification = org.specs2.mutable.Specification

  def wait(seconds:Float) = {

    val x = (seconds * 1000).toInt

    Duration(x,MILLISECONDS)
  }

  def waitFor[T](future:Future[T])(implicit timeout:Duration = wait(5)):T = {
    Await.result(future,timeout)
  }
}
