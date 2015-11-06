import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._

case class Message(id:String,msg:String){
	val msg_size = msg.split(" ")
}


val ssc = new StreamingContext(sc, Seconds(3))

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
