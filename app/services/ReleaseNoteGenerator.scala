package services

import javax.inject.Inject

import connectors.GitHubConnector
import play.api.libs.json._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global._
import scala.concurrent.ExecutionContext

class ReleaseNoteGenerator @Inject()(gitHubConnector: GitHubConnector) {

  def messageExtractor (json: JsValue): List[String] = {
    (json \\ "message").map(_.asOpt[String].getOrElse("Cannot find field")).toList
  }

  def getReleases(json: JsValue): (List[String], Map[String,String]) = {
    (json \\ "tag_name").map(_.asOpt[String].getOrElse("Cannot find field")).toList
    val listOfObjects: List[JsObject] = json.asOpt[List[JsObject]] match {
      case Some(x) => x
      case _ => List.empty[JsObject]
    }
    val listOfReleases = listOfObjects.map(releaseObject => (releaseObject \ "tag_name")
      .asOpt[String].getOrElse("Cannot find field"))

    val listOfReleaseCreated = listOfObjects.map(releaseObject => (releaseObject \ "created_at")
      .asOpt[String].getOrElse("Cannot find field"))

    val mappedDates: Map[String,String] = (listOfReleases.reverse zip listOfReleaseCreated.reverse).toMap

    (listOfReleases, mappedDates)
  }

  def getReleaseDates(repo: String, release: String, releases: Option[Map[String, String]])(implicit ec: ExecutionContext) : Future[(String, String)] = {
    gitHubConnector.getReleases(repo).map(response => response.json)
    .map(json => releases match {
      case Some(x) => findDates(release, x)
      case None => findDates(release, getReleases(json)._2)
    })
  }

    def findDates(release: String, releases: Map[String, String]): (String, String) = {
      def recursive(release: String, releases: Map[String, String], sinceReleaseDate: String): (String, String) = {
        releases match {
          case tagsAndDates if tagsAndDates.head._1 == release => (sinceReleaseDate, tagsAndDates.head._2)
          case tagsAndDates => recursive(release, tagsAndDates.tail, tagsAndDates.head._2)
        }
      }
        recursive(release,releases,"")
      }
    }
