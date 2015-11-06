package streaming

/**
 * Created by ldurette on 11/3/15.
 *
 */

//import org.apache.flink.streaming.api.datastream.DataStreamSource
//import org.apache.flink.streaming.api.environment.{StreamExecutionEnvironment, StreamContextEnvironment}
//import org.apache.flink.streaming.api.datastream._
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala._
import scala.reflect.ClassTag

//import org.apache.flinkclient.LocalExecutor
//import org.apache.flinkapi.scala.operators._

import scala.reflect.ClassTag

object FlinkStreamingGroupby {



    def main(args: Array[String]) {

      val env = StreamExecutionEnvironment.getExecutionEnvironment
      val raw:DataStream[String] = env.socketTextStream("localhost", 9999)
      //raw.print()

      //val split:DataStream[String] = raw.map{line:String => line.split('|')(0) }
        //.map{ msg:String => (msg, 1) }.groupBy(0).sum(1)

      /*
      val counts = text.flatMap { _.toLowerCase.split("\\W+") filter { _.nonEmpty } }
        .map { (_, 1) }
        .groupBy(0)
        .sum(1)

      counts.print
      */
      env.execute("Scala Socket Stream WordCount")
    }

}
