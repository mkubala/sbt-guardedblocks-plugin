package com.softwaremill.guardedblocks.sbtPlugin

import sbt.Keys._
import sbt._

object GuardedBlocksSbtPlugin extends GuardedBlocksSbtPlugin

class GuardedBlocksSbtPlugin extends sbt.AutoPlugin {

  val GuardedBlocksScalacPluginArtifact = "scalacplugin"
  val GuardedBlocksScalacPluginVersion = "0.1-SNAPSHOT"

  override def projectSettings: Seq[Def.Setting[_]] = super.projectSettings ++ Seq(
    libraryDependencies ++= Seq(
      "com.softwaremill.guardedblocks" % s"${GuardedBlocksScalacPluginArtifact}_2.11" % GuardedBlocksScalacPluginVersion % "provided"
    ),
    scalacOptions ++= {
      val updateRes = update.value
      val x = updateRes matching configurationFilter("provided")
      x.find(_.getName.contains(GuardedBlocksScalacPluginArtifact)) match {
        case None =>
          throw new Exception(s"Fatal: $GuardedBlocksScalacPluginArtifact not in libraryDependencies!")
        case Some(compilerPluginJar) =>
          Seq(s"-Xplugin:${compilerPluginJar.absolutePath}")
      }
    }
  )
}
