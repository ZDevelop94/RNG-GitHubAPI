package connectors

import config.Config
import javax.inject.{Inject, Singleton}

import scala.concurrent._
import play.api.libs.ws.{WSClient, WSResponse}
import play.api.Logger
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class GitHubConnectorImpl @Inject()(ws: WSClient) extends GitHubConnector with Config {

  def getCommits (since: String, until: String, user: String, repo: String): Future[WSResponse] = {

    since match {
      case value if value.isEmpty => sendRequest(defaultDate, user, repo, until)
      case value => sendRequest(value, user, repo, until)
    }
  }

  def getReleases(user: String, repo: String) : Future[WSResponse] = {
    val url = baseUrl + s"/$user/$repo/releases"
    ws.url(url).withHeaders("Accept" -> "application/vnd.github.v3+json")
      .get()
  }

  private def sendRequest(date: String, user: String, repo: String, until: String): Future[WSResponse] = {
    val request = ws.url(baseUrl + s"/$user/$repo/commits")
      .withQueryString("since" -> date, "until" -> until)
      .withHeaders("Accept" -> "application/vnd.github.v3+json")


    Logger.info(s"Requested URL: ${request.url}")
    //println(s"Requested URL: ${request.url}")

    request.get()
  }
}
