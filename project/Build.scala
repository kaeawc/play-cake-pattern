import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-cake"
  val appVersion      = "1.0-SNAPSHOT"
  val appDependencies = Seq(
    jdbc,
    anorm,
    "com.typesafe.play.extras" %% "iteratees-extras" % "1.0.1",
    "org.specs2" %% "specs2" % "2.1.1",
    "org.mockito" % "mockito-all" % "1.9.0"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-deprecation",
      "-unchecked",
      "-feature"
    ),
    resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                    "releases"  at "http://oss.sonatype.org/content/repositories/releases")
  )

}
