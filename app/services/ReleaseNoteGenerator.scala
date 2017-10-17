package services

import javax.inject.Inject

import connectors.GitHubConnector
import scala.concurrent.Future
import play.api.libs.json._

class ReleaseNoteGenerator @Inject()(gitHubConnector: GitHubConnector) {

  def messageExtractor (json: JsValue): List[String] = {
     (json \\ "message").map(_.asOpt[String].getOrElse("Cannot find field")).toList
  }

  def getReleases(json: JsValue) = {
    (json \\ "tag_name").map(_.asOpt[String].getOrElse("Cannot find field")).toList
  }
}
