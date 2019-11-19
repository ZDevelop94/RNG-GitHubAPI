package controllers

import javax.inject.Inject
import connectors.GitHubConnectorImpl
import exceptions.HttpException
import play.api.libs.json.JsValue
import play.api.mvc.{Action, Controller}
import services.ReleaseNoteGenerator

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ReleaseNoteController @Inject()(releaseNoteGenerator: ReleaseNoteGenerator, gitHubConnector: GitHubConnectorImpl) extends Controller {

  def showCommits(user: String, repo: String, release: String, releases: Option[Map[String,String]]) = Action.async {
    releaseNoteGenerator.getReleaseDates(user, repo, release, releases)
      .flatMap(dates => gitHubConnector.getCommits(dates._1, dates._2, user, repo)
        .map { response =>
          response.status match {
            case 200 => releaseNoteGenerator.messageExtractor(response.json)
            case _ => HttpException(response.status, "commit_url")
              List.empty[String]
          }
        }
      ) recover {
      case _: Exception => List.empty[String]
    } map (commits => Ok(views.html.showReleaseNote(commits, user, repo)))
  }

  def getReleases(user: String, repo: String) = Action.async {

    val jsonFuture: Future[JsValue] = gitHubConnector.getReleases(user, repo).map(response => response.json)

    val releasesFuture: Future[(List[String],Map[String,String])] = jsonFuture.map(json =>
      releaseNoteGenerator.getReleases(json))

    releasesFuture.map(releases => Ok(views.html.showReleases(user, repo, releases._1, releases._2)))
  }
}