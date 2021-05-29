inThisBuild(List(
  organization := "io.github.typeness",
  homepage := Some(url("https://github.com/typeness/spray-json-refined")),
  licenses := List("MIT" -> url("https://github.com/typeness/spray-json-refined/blob/master/LICENSE")),
  developers := List(
    Developer(
      "typeness",
      "typeness",
      "tangens270@gmail.com",
      url("https://github.com/typeness")
    )
  )
))

name := "spray-json-refined"

crossScalaVersions := List("2.13.5", "2.12.14")

scalacOptions ++= Seq(
  "-encoding", "utf8",
  "-deprecation",
  "-language:higherKinds"
)

libraryDependencies ++= Seq(
  "io.spray" %% "spray-json" % "1.3.6",
  "eu.timepit" %% "refined" % "0.9.26",
  "org.scalatest" %% "scalatest" % "3.2.9" % Test
)
