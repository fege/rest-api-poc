package com.ayurchuk.blogs.restapi.resources

import com.ayurchuk.blogs.restapi.actors.UID
import spray.http.HttpResponse
import spray.httpx.unmarshalling.{FromResponseUnmarshaller, MalformedContent}
import spray.json.{DefaultJsonProtocol, _}


object JsonProtocol extends DefaultJsonProtocol {

  implicit val uidUnmarshaller = new FromResponseUnmarshaller[UID] {
    implicit val uidFormat = jsonFormat1(UID)

    def apply(response: HttpResponse) = try {
      Right(response.entity.asString.parseJson.convertTo[UID])
    } catch {
      case x: Throwable => Left(MalformedContent("Couldn't unmarshal UID.", x))
    }
  }

  implicit val uidFormat = jsonFormat1(UID)
}

