#!/bin/sh
echo "Start Echo Server"
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
	var logSize = 10
	
	while (true) {
    //Thread.sleep(100);
		val s = server.accept()
    //val in = new BufferedSource(s.getInputStream()).getLines()
		//in.map(_.split(" ")).count()
    val out = new PrintStream(s.getOutputStream())
		
		for( x <- 1 to logSize ) {
			val se = ServerEvent() 
    	out.println(se.getLog)
		}
		logSize = gen(200)
    out.flush()
		s.close()
    
	}
	
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
