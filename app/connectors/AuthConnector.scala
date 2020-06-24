package connectors

import auth.{AuthState, OAuthToken}
import config.Config
import converters.JsonConverters
import exceptions.HttpException
import javax.inject.Inject

import scala.concurrent._
import play.api.libs.ws.WSClient
import play.api.Logger
import play.api.libs.json.JsValue

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Try

class AuthConnector @Inject()(ws: WSClient) extends Config with JsonConverters{
  def authExchange(code:String)(implicit authState: AuthState): Future[Either[Throwable, OAuthToken]] = {
    val url = baseUrl + s"/login/oauth/access_token"
    val query = Map(
      "client_id" -> clientId,
      "client_secret" -> clientSecret,
      "code" -> code,
      "redirect_uri" -> redirectUri,
      "state" -> authState.value
    )

    sendRequest(url, query).map { jsvalue =>
      (Try {
        Right(jsvalue.as[OAuthToken])
      } recover {
        case e: Exception => Left(e)
      }).get
    }
  }

  private def sendRequest(url: String, query: Map[String, String]): Future[JsValue] = {// TODO Make this method more general
    val request = ws.url(url)
      .withQueryString(parameters = query.toSeq: _*)
      .withHeaders("Accept" -> "application/json")

    Logger.info(s"Requested URLLLL: ${request.uri.toString}")
    //println(s"Requested URL: ${request.url}")

    request.get().map { response =>
      response.status match {
        case 200 => println("YESSSSSS")
          response.json
        case _ => println("NOOOOOOO")
          throw HttpException(response.status, url)
      }
    }
  }
}