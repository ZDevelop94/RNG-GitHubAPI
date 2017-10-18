package services

import javax.inject.Inject
import play.api.libs.json._

class ReleaseNoteGenerator @Inject()() {

  def messageExtractor (json: JsValue): List[String] = {
    (json \\ "message").map(_.asOpt[String].getOrElse("Cannot find field")).toList
  }

  def getReleases(json: JsValue) = {
    (json \\ "tag_name").map(_.asOpt[String].getOrElse("Cannot find field")).toList
    val listOfObjects: List[JsObject] = json.asOpt[List[JsObject]] match {
      case Some(x) => x
      case _ => List.empty[JsObject]
    }
    listOfObjects.map(releaseObject => (releaseObject \ "tag_name")
      .asOpt[String].getOrElse("Cannot find field"))
  }
}
