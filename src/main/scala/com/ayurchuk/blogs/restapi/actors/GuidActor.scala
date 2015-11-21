package com.ayurchuk.blogs.restapi.actors

import java.util.UUID

import akka.actor.Actor
import com.ayurchuk.blogs.restapi.actors.GuidActor.NewGuid

object GuidActor {
  case object NewGuid
}

class GuidActor extends Actor {

  def receive = {
    case NewGuid => sender ! new UID(UUID.randomUUID().toString)
  }
}

case class UID(id: String)