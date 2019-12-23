name := "bookmark"

organization := "com.aroundus.example"

version := "0.1.0"

scalaVersion := "2.12.10"

scalafmtOnCompile := true

Global / onChangedBuildSource := ReloadOnSourceChanges

lazy val versions = new {
  val finatra         = "19.12.0"
  val guice           = "4.2.2"
  val scalatest       = "3.0.8"
  val logback         = "1.2.3"
  val slick           = "3.3.2"
  val jackson         = "2.9.10"
  val jacksonDatabind = "2.9.10.1"
}

libraryDependencies ++= Seq(
  "com.twitter"                    %% "finatra-http"         % versions.finatra,
  "com.twitter"                    %% "finatra-jackson"      % versions.finatra,
  "com.twitter"                    %% "finatra-validation"   % versions.finatra,
  "ch.qos.logback"                 % "logback-classic"       % versions.logback,
  "com.typesafe.slick"             %% "slick"                % versions.slick,
  "com.typesafe.slick"             %% "slick-hikaricp"       % versions.slick,
  "com.h2database"                 % "h2"                    % "1.4.199",
  "joda-time"                      % "joda-time"             % "2.10.4",
  "com.fasterxml.jackson.core"     % "jackson-core"          % versions.jackson,
  "com.fasterxml.jackson.core"     % "jackson-databind"      % versions.jacksonDatabind,
  "com.fasterxml.jackson.module"   %% "jackson-module-scala" % versions.jackson,
  "com.fasterxml.jackson.datatype" % "jackson-datatype-joda" % versions.jackson,
  "com.twitter"                    %% "finatra-http"         % versions.finatra % Test,
  "com.twitter"                    %% "finatra-jackson"      % versions.finatra % Test,
  "com.twitter"                    %% "finatra-validation"   % versions.finatra % Test,
  "com.twitter"                    %% "inject-server"        % versions.finatra % Test,
  "com.twitter"                    %% "inject-app"           % versions.finatra % Test,
  "com.twitter"                    %% "inject-core"          % versions.finatra % Test,
  "com.twitter"                    %% "inject-modules"       % versions.finatra % Test,
  "com.google.inject.extensions"   % "guice-testlib"         % versions.guice % Test,
  "com.twitter"                    %% "finatra-http"         % versions.finatra % Test classifier "tests",
  "com.twitter"                    %% "finatra-jackson"      % versions.finatra % Test classifier "tests",
  "com.twitter"                    %% "finatra-validation"   % versions.finatra % Test classifier "tests",
  "com.twitter"                    %% "inject-server"        % versions.finatra % Test classifier "tests",
  "com.twitter"                    %% "inject-app"           % versions.finatra % Test classifier "tests",
  "com.twitter"                    %% "inject-core"          % versions.finatra % Test classifier "tests",
  "com.twitter"                    %% "inject-modules"       % versions.finatra % Test classifier "tests",
  "org.scalatest"                  %% "scalatest"            % versions.scalatest % Test
)
