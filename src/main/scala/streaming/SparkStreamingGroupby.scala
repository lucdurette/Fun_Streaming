package streaming

/**
 * Created by ldurette on 10/23/15.
 */
import org.apache.spark._
import org.apache.spark.rdd._
import org.apache.spark.sql.{SQLContext,DataFrame,functions}
import org.apache.spark.sql.functions._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._

object SparkStreamingGroupby {

  case class Message(id:String,msg:String){
    val msg_size = msg.split(" ")
  }
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext()

    val sqlContext= new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._

    val ssc = new StreamingContext(sc, Seconds(1))

    //val lines = ssc.socketTextStream("localhost", 9999)

    val lines = ssc.socketTextStream("ldurette-orbitz.duncllc.com", 9999)
    val raw = lines.flatMap( _.split("\n") )
    //raw.print()
    val received = raw.map( _.split('|') ).map( a => Message(a(0), a(1)) )
    //received.print()

    val window_received = received.window(Seconds(15),Seconds(9))
    /*
    println("Dstream dataframe")
    received.foreachRDD { rdd =>
      rdd.toDF().groupBy("id").count().show()
    }
    */
    //window_received.print()

    println("Window dataframe")
    window_received.foreachRDD { rdd =>
      rdd.toDF().groupBy("id").count().orderBy(desc("count")).limit(4).show()
    }
    ssc.start()
    ssc.awaitTermination()

  }

}
