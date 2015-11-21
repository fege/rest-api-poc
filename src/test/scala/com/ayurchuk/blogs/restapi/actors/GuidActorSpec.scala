package com.ayurchuk.blogs.restapi.actors

import akka.actor.ActorSystem
import akka.testkit.{DefaultTimeout, ImplicitSender, TestActorRef, TestKit}
import com.ayurchuk.blogs.restapi.actors.GuidActor.NewGuid
import org.scalatest.{Matchers, WordSpecLike}

import scala.concurrent.duration._

class GuidActorSpec extends TestKit(ActorSystem("GuidActorSpec"))
                with DefaultTimeout with ImplicitSender with WordSpecLike with Matchers {

  val actorRef = TestActorRef(new GuidActor)

  "GuidActor" should {

    "return newly created uid for NewGuid message" in {
      within(50 milliseconds) {
        actorRef ! NewGuid

        fishForMessage() {
          case msg: UID ⇒ msg.id.length == 36
          case _ ⇒ false
        }
      }
    }
  }
}