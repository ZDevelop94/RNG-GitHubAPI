package services

import connectors.GitHubConnector
import javax.inject.Inject
import play.api.libs.json._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.ExecutionContext

class ReleaseNoteGeneratorImpl @Inject()(gitHubConnector: GitHubConnector) extends ReleaseNoteGenerator {

  def messageExtractor (json: JsValue): List[String] = {
    val zippedList : Seq[(String, String)] = (json \\ "sha").map(_.asOpt[String].getOrElse("Cannot find field")).toList zip
    (json \\ "message").map(_.asOpt[String].getOrElse("Cannot find field"))

    zippedList.map(tupleString => s"\n sha : ${tupleString._1} \n message: ${tupleString._2}").toList
  }

  def getReleases(user: String, repo: String): Future[(List[String],Map[String,String])] = {
      gitHubConnector.fetchReleases(user, repo) map { json =>
        (json \\ "tag_name").map(_.asOpt[String].getOrElse("Cannot find field")).toList

        json.asOpt[List[JsObject]] match {
        case Some(value) => value
        case _ => List.empty[JsObject]
      }
    } map { listOfObjects =>

        val listOfReleases = listOfObjects.map(releaseObject => (releaseObject \ "tag_name")
          .asOpt[String].getOrElse("Cannot find field"))

        val listOfReleaseCreated = listOfObjects.map(releaseObject => (releaseObject \ "created_at")
          .asOpt[String].getOrElse("Cannot find field"))

        val mappedDates: Map[String, String] = (listOfReleases.reverse zip listOfReleaseCreated.reverse).toMap

        (listOfReleases, mappedDates)
      }
  }

  def getCommits(user: String, repo: String, release: String, releases: Option[Map[String,String]]): Future[List[String]] = {
   getReleaseDates(user, repo, release, releases)
     .flatMap { dates =>
       gitHubConnector.fetchCommits(dates._1, dates._2, user, repo)
         .map{value =>
           println("value: " + value)
           val x = messageExtractor(value)
           println("dfdfdf : " + x)
           x
         }
     } recover {
     case e: Exception => println(s"EXCPTION : ${e.getMessage}")
       List.empty[String]
   }
  }

  def getReleaseDates(user: String, repo: String, release: String, releases: Option[Map[String, String]])(implicit ec: ExecutionContext) : Future[(String, String)] = {
    releases match {
      case Some(value) => Future(findDates(release, value))
      case None =>  getReleases(user, repo).map(tuple => findDates(release, tuple._2))
    }
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
