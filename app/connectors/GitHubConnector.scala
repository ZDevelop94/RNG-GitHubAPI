package connectors

import javax.inject.{Inject, Singleton}
import scala.concurrent._
import play.api.libs.ws.{WSClient, WSResponse}
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class GitHubConnector @Inject() (ws: WSClient) {

  val baseUrl = "https://api.github.com/repos/HMRC/agent-kyc"

  def getCommits (since: String, until: String): Future[WSResponse] = {
    val url =  baseUrl + "/commits"
    ws.url(url).withQueryString(
      "since" -> since, "until" -> until).withHeaders(
      "Accept" -> "application/vnd.github.v3+json")
      .get().map { response => response }
  }

  def getReleases(repo: String) : Future[WSResponse] = {
    val url = baseUrl + s"/$repo/releases"
    ws.url(url).withHeaders("Accept" -> "application/vnd.github.v3+json")
      .get().map { response => response }
  }
}
