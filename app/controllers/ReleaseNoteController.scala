package controllers

import javax.inject.Inject
import connectors.GitHubConnector
import play.api.mvc.{Action, Controller}
import services.ReleaseNoteGenerator

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ReleaseNoteController @Inject()(releaseNoteGenerator: ReleaseNoteGenerator, gitHubConnector: GitHubConnector) extends Controller {

  def showReleaseDetails(user: String, repo: String, release: String, releases: Option[Map[String,String]]) = Action.async {
    val commits: Future[List[String]] = releaseNoteGenerator.getCommits(user, repo, release, releases)

    commits map (commits => Ok(views.html.showReleaseNote(commits, user, repo)))
  }

  def getReleases(user: String, repo: String) = Action.async {

      releaseNoteGenerator.getReleases(user, repo)
        .map(releases => Ok(views.html.showReleases(user, repo, releases._1, releases._2)))
  }
}