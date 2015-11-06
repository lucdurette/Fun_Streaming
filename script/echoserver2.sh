#!/bin/sh
echo "Start Echo Server 2"
exec scala "$0" "$@"
!#
import java.net._
import java.io._
import scala.io._
import java.time.{LocalDate,LocalDateTime}

object EchoServer extends App{
	println()
  println("I am in the echoserver")
	val gen = scala.util.Random.nextInt(_:Int)

	val server = new ServerSocket(9999)
	val s = server.accept()
	while (true) {
    Thread.sleep(10);
		
    val out = new PrintStream(s.getOutputStream())
		//val serverType = serverTypes( gen(serverTypes.length) )
		val se = new ServerEvent()
		println(se.getLog)
		out.println(se.getLog)
    out.flush()
    
	}
	s.close()

	case class ServerEvent(){

		val servers =	List("Webapp","Sort_Host","Hue Deamon",
			"Hive Server","Spark Worker","Spark Driver",
			"Impala Deamon","Thrift Server")
		val alerts = List("Critical","Alert","Emergency","Error","Warning")
		
		val ts = LocalDateTime.now() 
		val server = servers( gen(servers.length) )
		val alert = alerts( gen(alerts.length) )
		def getLog() = {
			List(server,alert,ts).mkString("|")
		}
	}
	
}
EchoServer.main(args)
