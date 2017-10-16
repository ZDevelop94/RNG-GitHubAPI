package connectors

import javax.inject.{Inject, Singleton}

import scala.concurrent._
import play.api.libs.ws.{WSClient, WSResponse}
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class GitHubConnector @Inject() (ws: WSClient) {

  def getCommits (since: String, until: String): Future[WSResponse] = {
    val url = "https://api.github.com/repos/HMRC/agent-kyc/commits"
    ws.url(url).withQueryString(
      "since" -> since,
      "until" -> until)
      .withHeaders(
        "Accept" -> "application/vnd.github.v3+json")
      .get()
      .map { response => response }
  }
}
