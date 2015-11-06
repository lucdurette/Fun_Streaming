package fakerecords

/**
 * Created by ldurette on 11/5/15.
 */

import java.time.{LocalDate,LocalDateTime}

case class ServerEvent(){

  val gen = scala.util.Random.nextInt(_:Int)

  val servers =	List("Webapp","Sort_Host","Hue Deamon",
    "Hive Server","Spark Worker","Spark Driver",
    "Impala Deamon","Thrift Server")
  val alerts = List("Critical","Alert","Emergency","Error","Warning")

  val ts = LocalDateTime.now()
  val server = servers( gen(servers.length) )
  val alert = alerts( gen(alerts.length) )
  def getLog():String = {
    List(server,alert,ts).mkString("|")
  }
}
