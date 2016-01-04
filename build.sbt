import com.typesafe.sbt.SbtScalariform.{ScalariformKeys, _}
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import sbt.dsl._
import spray.revolver.RevolverPlugin.Revolver

import scalariform.formatter.preferences.{AlignSingleLineCaseStatements, DoubleIndentClassDeclaration, AlignParameters}

name := """innkeeper"""
organization  := "org.zalando.spearheads"
version       := "0.0.1"

mainClass in Compile := Some("org.zalando.spearheads.innkeeper.Innkeeper")

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")

resolvers += "Whisk" at "https://dl.bintray.com/whisk/maven"
resolvers += "softprops-maven" at "http://dl.bintray.com/content/softprops/maven"

val akkaStreamV      = "1.0"
val scalaTestV       = "3.0.0-M1"
val scalaMockV       = "3.2.2"

libraryDependencies ++= List(
  "com.typesafe.slick"      %% "slick"                                % "3.0.1",
  "com.h2database"           % "h2"                                   % "1.3.175",
  "com.typesafe.akka"       %% "akka-stream-experimental"             % akkaStreamV,
  "com.typesafe.akka"       %% "akka-http-core-experimental"          % akkaStreamV,
  "com.typesafe.akka"       %% "akka-http-spray-json-experimental"    % akkaStreamV,
  "com.typesafe.akka"       %% "akka-slf4j"                           % "2.3.9",
  "ch.qos.logback"           % "logback-classic"                      % "1.1.3",
  "com.google.inject"        % "guice"                                % "4.0",
  "net.codingwell"          %% "scala-guice"                          % "4.0.0",
  "org.postgresql"           % "postgresql"                           % "9.3-1103-jdbc41",
  "com.github.tminglei"     %% "slick-pg"                             % "0.9.1",
  "com.zaxxer"               % "HikariCP"                             % "2.4.1",
  "nl.grons"                %% "metrics-scala"                        % "3.5.2",
  "org.scalatest"           %% "scalatest"                            % scalaTestV       % "it,test",
  "org.scalamock"           %% "scalamock-scalatest-support"          % scalaMockV       % "it,test",
  "com.typesafe.akka"       %% "akka-http-testkit-experimental"       % akkaStreamV      % "it,test",
  "com.typesafe.akka"       %% "akka-stream-testkit-experimental"     % akkaStreamV      % "it,test",
  "com.whisk"               %% "docker-testkit-scalatest"             % "0.4.0"          % "it,test",
  "com.whisk"               %% "docker-testkit-config"                % "0.4.0"          % "it,test"
)

lazy val root = project.in(file(".")).configs(IntegrationTest)
Defaults.itSettings
scalariformSettings
Revolver.settings

enablePlugins(JavaAppPackaging)

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(AlignParameters, true)

fork in run := true
