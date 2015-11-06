name := "streaming"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.10" % "1.5.1" % "provided",
  "org.apache.spark" %% "spark-sql" % "1.5.1" % "provided",
  "org.apache.spark" %% "spark-hive" % "1.5.1" % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.5.1" % "provided",
  "org.apache.flink" % "flink-scala" % "0.9.1" % "provided",
  //"org.apache.flink" % "flink-streaming-core" % "0.9.1" % "provided",
  "org.apache.flink" % "flink-clients" % "0.9.1" % "provided",
  "org.apache.flink" % "flink-streaming-scala" % "0.9.1" % "provided",
  "org.apache.kafka" % "kafka_2.10" % "0.8.2.1"
    //exclude("javax.jms", "jms")
    //exclude("com.sun.jdmk", "jmxtools")
    //exclude("com.sun.jmx", "jmxri")
)

fork in run := true