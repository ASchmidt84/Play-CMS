name := "play-slick-bootstrap3"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  "com.typesafe.slick" %% "slick" % "2.1.0-M2",
  cache,
  ws
)
