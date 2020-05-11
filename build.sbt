name := """ZGitHub"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
import play.sbt.routes.RoutesKeys.routesImport

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.reactivemongo"         %% "play2-reactivemongo"  % "0.12.6-play26",
  "org.scalatestplus.play"    %% "scalatestplus-play"   % "1.5.1" % Test,
  "org.scalatest"             %  "scalatest_2.11"       % "2.2.2" % "test",
  "com.typesafe"              %  "config"               % "1.2.1",
  "org.mockito"               % "mockito-core"          % "3.2.4" % Test,
  "org.json4s"                %% "json4s-jackson"       % "3.6.7"
)

routesImport += "binders.CustomQueryStringBinder._"

