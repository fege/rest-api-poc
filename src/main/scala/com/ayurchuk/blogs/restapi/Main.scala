package com.ayurchuk.blogs.restapi

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import com.ayurchuk.blogs.restapi.services.GuidServiceActor
import spray.can.Http

object Main extends App {
  implicit val system = ActorSystem("Rest-Service")

  val guidService = system actorOf Props(new GuidServiceActor)

  val guidGeneratorHost = system.settings.config.getString("guid-generator.host")
  val guidGeneratorPort = system.settings.config.getInt("guid-generator.port")

  IO(Http) ! Http.Bind(guidService, guidGeneratorHost, guidGeneratorPort)

  sys.addShutdownHook(system.terminate())
}
