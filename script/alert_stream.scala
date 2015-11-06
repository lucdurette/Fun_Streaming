import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import java.time.{LocalDate,LocalDateTime}

case class Alert(host:String,message:String,ts:String)

val ssc = new StreamingContext(sc, Seconds(5))

//val lines = ssc.socketTextStream("localhost", 9999)

val lines = ssc.socketTextStream("ldurette-orbitz.duncllc.com", 9999)

val raw = lines.flatMap( _.split("\n") )

//raw.print()
val received = raw.map( _.split('|') ).map( a => Alert(a(0),a(1),a(2)) )
received.print()

received.foreachRDD { rdd =>
	val res = rdd.toDF().groupBy("host").count()
	res.show()
}


ssc.start()
ssc.awaitTermination()
