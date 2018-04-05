package controllers

import javax.inject.Inject

import connectors.GitHubConnector
import play.api.libs.json.JsValue
import play.api.mvc.{Action, Controller, QueryStringBindable}
import services.ReleaseNoteGenerator

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.Duration

class ReleaseNoteController @Inject()(releaseNoteGenerator: ReleaseNoteGenerator, gitHubConnector: GitHubConnector) extends Controller {

  def showCommits(user: String, repo: String, release: String, releases: Option[Map[String,String]]) = Action.async {

    val futureDates = releaseNoteGenerator.getReleaseDates(user, repo, release, releases)
      .map(dates => gitHubConnector.getCommits(dates._1, dates._2, user, repo)
        .map(response => releaseNoteGenerator.messageExtractor(response.json)))

    val result = futureDates.map(_.map(x => Ok(views.html.showReleaseNote(x, user, repo))))

    result.flatMap(x => x)
  }

  def getReleases(user: String, repo: String) = Action.async {

    val jsonFuture: Future[JsValue] = gitHubConnector.getReleases(user, repo).map(response => response.json)

    val releasesFuture: Future[(List[String],Map[String,String])] = jsonFuture.map(json =>
      releaseNoteGenerator.getReleases(json))

    releasesFuture.map(releases => Ok(views.html.showReleases(user, repo, releases._1, releases._2)))
  }
}