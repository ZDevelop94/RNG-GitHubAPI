name := """ZolvedIT"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.scalatest" % "scalatest_2.11" % "2.2.2" % "test",
  "org.reactivemongo" %% "play2-reactivemongo" % "0.12.6-play26"
)
