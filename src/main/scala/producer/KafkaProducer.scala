package producer

/**
 * Created by ldurette on 11/4/15.
 */

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.Properties
import fakerecords.{ServerEvent, UserEvent}

object KafkaProducer {

  val gen = scala.util.Random.nextInt(_:Int)

  def main(args: Array[String]): Unit = {
    val topic = args(0)
    val brokerList = args(1)
    val zookeeperConnect = args(2)

    val properties = new Properties()

    properties.put("bootstrap.servers", brokerList);
    properties.put("key.serializer", classOf[org.apache.kafka.common.serialization.StringSerializer])
    properties.put("value.serializer", classOf[org.apache.kafka.common.serialization.StringSerializer])

    val producer:KafkaProducer[String,String] = new KafkaProducer(properties)

    while(true){
      val se = ServerEvent()
      producer.send(new ProducerRecord(topic, se.getLog ))
      Thread.sleep(100)
    }
    producer.close()
  }

}
