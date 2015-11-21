package com.ayurchuk.blogs.restapi.services

import com.ayurchuk.blogs.restapi.actors.UID
import com.ayurchuk.blogs.restapi.resources.JsonProtocol._
import org.specs2.mutable.Specification
import spray.json._
import spray.testkit.Specs2RouteTest

class GuidServiceSpec extends Specification with Specs2RouteTest with GuidService {

  def actorRefFactory = system

  "The Guid Service" should {

    "generate UID for /api/v1/guids" in {
      Get("/api/v1/guids") ~> guidRoute ~> check {
        val uidJson = responseAs[String].parseJson
        val uid = uidJson.convertTo[UID]

        uid.id.length === 36
      }
    }
  }
}