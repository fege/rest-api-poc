package com.ayurchuk.blogs.restapi.directives

import akka.actor.ActorRef
import akka.pattern.ask
import com.ayurchuk.blogs.restapi.actors.UID
import com.ayurchuk.blogs.restapi.resources.JsonProtocol._
import com.ayurchuk.blogs.restapi.actors.GuidActor.NewGuid
import spray.httpx.SprayJsonSupport._

import scala.concurrent.ExecutionContext

class GuidDirective(guidActor: ActorRef)
                   (implicit executionContext: ExecutionContext) extends CommonDirective {

  val route = apiPrefix {
    path("guids") {
      get {
        complete {
          (guidActor ? NewGuid).mapTo[UID]
        }
      }
    }
  }
}