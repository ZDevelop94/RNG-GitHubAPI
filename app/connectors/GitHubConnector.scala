package connectors

import javax.inject.{Inject, Singleton}
import scala.concurrent._
import play.api.libs.ws.{WSClient, WSResponse}
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class GitHubConnector @Inject() (ws: WSClient) {

  val baseUrl = "https://api.github.com/repos"
  val defaultDate = "2000-01-01T00:00:00Z"

  def getCommits (since: String, until: String, user: String, repo: String): Future[WSResponse] = {

    def sendRequest(date: String): Future[WSResponse] = {
      val url = baseUrl + s"/$user/$repo/commits"
      ws.url(url).withQueryString(
        "since" -> date, "until" -> until).withHeaders(
        "Accept" -> "application/vnd.github.v3+json")
        .get()
    }

    since match {
      case x if x == "" => sendRequest(defaultDate)
      case x => sendRequest(x)
    }
  }

  def getReleases(user: String, repo: String) : Future[WSResponse] = {
    val url = baseUrl + s"/$user/$repo/releases"
    ws.url(url).withHeaders("Accept" -> "application/vnd.github.v3+json")
      .get()
  }
}
