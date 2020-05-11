package connectors

import play.api.libs.json.JsValue

import scala.concurrent.Future

trait GitHubConnector {

  def fetchCommits (since: String, until: String, user: String, repo: String): Future[JsValue]

  def fetchReleases (user: String, repo: String) : Future[JsValue]
}
