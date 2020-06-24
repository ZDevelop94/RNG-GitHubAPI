package connectors

import config.Config
import exceptions.HttpException
import javax.inject.Inject

import scala.concurrent._
import play.api.libs.ws.WSClient
import play.api.Logger
import play.api.libs.json.JsValue

import scala.concurrent.ExecutionContext.Implicits.global

class GitHubConnector @Inject()(ws: WSClient) extends Config {

  def fetchCommits(since: String, until: String, user: String, repo: String): Future[JsValue] = {

    val url = apiBaseUrl + s"/$user/$repo/commits"
    val sinceDate = if(since.nonEmpty) since else defaultDate
    val query: Map[String, String] = Map("since" -> sinceDate, "until" -> until)

    sendRequest(url, query)
  }

  def fetchReleases(user: String, repo: String): Future[JsValue] = {
    val url = apiBaseUrl + s"/$user/$repo/releases"
    sendRequest(url, Map.empty)
  }

  private def sendRequest(url: String, query: Map[String, String]): Future[JsValue] = {
    val request = ws.url(url)
      .withQueryString(parameters = query.toSeq: _*)
      .withHeaders("Accept" -> "application/vnd.github.v3+json")

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
