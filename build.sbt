name := """education"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "commons-beanutils" % "commons-beanutils" % "1.9.2",
  "com.google.guava" % "guava" % "18.0"
)
