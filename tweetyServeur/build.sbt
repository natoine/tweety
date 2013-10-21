name := "tweetyServeur"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)     

libraryDependencies += "org.apache.jena" % "apache-jena-libs" % "2.11.0"

play.Project.playJavaSettings
