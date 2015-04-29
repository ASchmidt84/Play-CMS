name := "play-slick-bootstrap3"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  "com.typesafe.slick" %% "slick" % "2.1.0-M2",
  "org.joda" % "joda-convert" % "1.6",
  "com.github.tototoshi" %% "slick-joda-mapper" % "1.2.0",
  "com.mohiva" %% "play-html-compressor" % "0.3.1",
  "com.loicdescotte.coffeebean" %% "html5tags" % "1.2.1",
  "com.adrianhurt" %% "play-bootstrap3" % "0.4.2-SNAPSHOT",
  //"com.monochromeroad" % "play-xwiki-rendering_2.11" % "1.0",
  //"org.xwiki.rendering" % "xwiki-rendering-macro-comment" % "4.1.3",
  "jp.t2v" %% "play2-auth"      % "0.13.2",
  cache,
  ws
)

libraryDependencies += "joda-time" % "joda-time" % "2.6"

libraryDependencies += "com.h2database" % "h2" % "1.4.183"

libraryDependencies += "com.mchange" % "c3p0" % "0.9.5-pre10"

resolvers += Resolver.url("github repo for html5tags", url("http://loicdescotte.github.io/Play2-HTML5Tags/releases/"))(Resolver.ivyStylePatterns)

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

//resolvers += "Monochrmeroad CloudBees Repository" at "http://repository-monochromeroad.forge.cloudbees.com/release"