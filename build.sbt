name := "spray-json-refined"

version := "0.1"

scalaVersion := "2.13.2"

scalacOptions ++= Seq(
  "-encoding", "utf8",
  "-deprecation",
  "-language:higherKinds"
)

libraryDependencies ++= Seq(
  "io.spray" %% "spray-json" % "1.3.5",
  "eu.timepit" %% "refined" % "0.9.14",
  "org.scalatest" %% "scalatest" % "3.1.1" % Test
)
