name := "sbt-guardedblocks-plugin"

organization := "com.softwaremill.guardedblocks"

version := "0.1-SNAPSHOT"

sbtPlugin := true

libraryDependencies ++= Seq(
  "com.softwaremill.guardedblocks" % "scalacplugin_2.11" % "0.1-SNAPSHOT"
)

publishMavenStyle := false

publishArtifact in Test := false
