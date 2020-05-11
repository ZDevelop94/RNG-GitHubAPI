/*
package auth

import config.Config
import converters.JsonConverters
import exceptions.HttpException
import javax.inject.Inject
import play.api.libs.json.JsValue
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.libs.json.Json

import scala.util.Try
/*import org.json4s._
import org.json4s.jackson.{Json, Serialization}
import org.json4s.jackson.Serialization.read*/

class AuthenticationImpl @Inject()(ws: WSClient) extends Authentication with Config with JsonConverters {


  val authBody: JsValue = Json.parse(
    """
      |{
      |  "scopes": ["public_repo"],
      |   "note": "admin script"
      |}
      |""".stripMargin
  )
  val url = s"$baseUrl2$authInitialisePath"

  val getAuth: Map[String, String] = {
    val responseFuture: Future[WSResponse] = ws.url(url).post(authBody)
    responseFuture.map { response =>
      response.status match {
        case 200 => extractResponse(response)
        case _ => HttpException(response.status, "commit_url")
          List.empty[String]
      }
    }
  }

  def extractResponse(response: WSResponse ): Map[String, String] = {

    val authResponse: AuthResponse = Try(Json.fromJson[AuthResponse](response.json).get)
      .recover {
        case e: Exception =>
          println(s"Failed to convert Json to auth response model, reason : ${e.getMessage}")
          throw e
      }.get

  }
}
*/
