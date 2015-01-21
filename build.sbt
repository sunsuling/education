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
  "com.google.guava" % "guava" % "18.0",
  "org.webjars" % "angularjs" % "1.2.18" exclude("org.webjars", "jquery"),
  "org.webjars" % "angular-ui-router" % "0.2.11-1",
  "org.webjars" % "nervgh-angular-file-upload" % "1.1.5" ,
  "org.webjars" % "angular-ui" % "0.4.0-3",
  "org.webjars" % "ng-grid" % "2.0.14",
  "org.webjars" % "angular-ui-bootstrap" % "0.11.2",
  "org.webjars" % "angular-ui-date" % "0.0.5",
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "org.webjars" % "jquery" % "1.11.1"
)
