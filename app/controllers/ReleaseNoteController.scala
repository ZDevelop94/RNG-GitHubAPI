package controllers

import javax.inject.Inject
import connectors.GitHubConnector
import play.api.libs.json.JsValue
import play.api.mvc.{Action, Controller}
import services.ReleaseNoteGenerator
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ReleaseNoteController @Inject()(releaseNoteGenerator: ReleaseNoteGenerator, gitHubConnector: GitHubConnector) extends Controller {

  def showCommits(repo: String, release: String, releases: Option[Map[String,String]]) = Action.async {

    val jsonFuture: Future[JsValue] = gitHubConnector.getCommits("2017-01-01T00:00:00Z", "2018-01-01T00:00:00Z",repo)
      .map(response => response.json)

    val messagesFuture: Future[List[String]] = jsonFuture.map(json =>
      releaseNoteGenerator.messageExtractor(json))

    messagesFuture.map(messages => Ok(views.html.showReleaseNote(messages, repo)))
  }

  def getReleases(repo: String) = Action.async {

    val jsonFuture: Future[JsValue] = gitHubConnector.getReleases(repo).map(response => response.json)

    val releasesFuture: Future[(List[String],Map[String,String])] = jsonFuture.map(json =>
      releaseNoteGenerator.getReleases(json))

    releasesFuture.map(releases => Ok(views.html.showReleases(repo, releases._1, releases._2)))
  }
}