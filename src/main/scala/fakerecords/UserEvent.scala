package fakerecords

/**
 * Created by ldurette on 11/5/15.
 */

case class UserEvent(){
  val gen = scala.util.Random.nextInt(_:Int)

  val users =	List("Luc","Bowei","Wai Gen","Ken","Dany","Rob")
  val user = users( gen(users.length) )
}
