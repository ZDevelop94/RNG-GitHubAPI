package services

import play.api.libs.json.JsValue

import scala.concurrent.{ExecutionContext, Future}

trait ReleaseNoteGenerator {

  def messageExtractor (json: JsValue): List[String]

  def getReleases(user: String, repo: String): Future[(List[String],Map[String,String])]

  def getCommits(user: String, repo: String, release: String, releases: Option[Map[String,String]]): Future[List[String]]

  def getReleaseDates(user: String, repo: String, release: String, releases: Option[Map[String, String]])(implicit ec: ExecutionContext) : Future[(String, String)]

  def findDates(release: String, releases: Map[String, String]): (String, String)
}
