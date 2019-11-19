package connectors

import play.api.libs.ws.WSResponse

import scala.concurrent.Future

trait GitHubConnector {

  def getCommits (since: String, until: String, user: String, repo: String): Future[WSResponse]

  def getReleases (user: String, repo: String) : Future[WSResponse]
}
