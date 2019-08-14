name := "bookmark"

organization := "com.aroundus.example"

version := "0.1.0"

scalaVersion := "2.12.9"

scalafmtOnCompile := true

lazy val versions = new {
	val finatra = "19.8.0"
	val guice = "4.2.2"
	val scalatest = "3.0.8"
	val logback = "1.2.3"
	val slick = "3.3.2"
}

libraryDependencies ++= Seq(
	"com.twitter" %% "finatra-http" % versions.finatra,
	"ch.qos.logback" % "logback-classic" % versions.logback,
	"com.typesafe.slick" %% "slick" % versions.slick,
	"com.typesafe.slick" %% "slick-hikaricp" % versions.slick,
	"com.h2database" % "h2" % "1.4.199",

	"joda-time" % "joda-time" % "2.10.3",
	"com.fasterxml.jackson.core" % "jackson-core" % "2.9.9",
	"com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.9",
	"com.fasterxml.jackson.datatype" % "jackson-datatype-joda" % "2.9.9",

	"com.twitter" %% "finatra-http" % versions.finatra % Test,
	"com.twitter" %% "finatra-jackson" % versions.finatra % Test,
	"com.twitter" %% "inject-server" % versions.finatra % Test,
	"com.twitter" %% "inject-app" % versions.finatra % Test,
	"com.twitter" %% "inject-core" % versions.finatra % Test,
	"com.twitter" %% "inject-modules" % versions.finatra % Test,
	"com.google.inject.extensions" % "guice-testlib" % versions.guice % Test,

	"com.twitter" %% "finatra-http" % versions.finatra % Test classifier "tests",
	"com.twitter" %% "finatra-jackson" % versions.finatra % Test classifier "tests",
	"com.twitter" %% "inject-server" % versions.finatra % Test classifier "tests",
	"com.twitter" %% "inject-app" % versions.finatra % Test classifier "tests",
	"com.twitter" %% "inject-core" % versions.finatra % Test classifier "tests",
	"com.twitter" %% "inject-modules" % versions.finatra % Test classifier "tests",

	"org.scalatest" %% "scalatest" % versions.scalatest % Test
)
