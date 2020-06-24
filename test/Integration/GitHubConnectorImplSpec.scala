/*
package Integration

import connectors.GitHubConnectorImpl
import play.api.libs.json.JsValue
import play.api.test.Helpers._
import java.net.URLEncoder
import scala.concurrent.ExecutionContext.Implicits.global

import support.TestHelper

import scala.util.{Failure, Success}

class GitHubConnectorImplSpec extends TestHelper {

  val baseUrlStub:String = "http://localhost:8080/repos"

  val connector = new GitHubConnectorImpl(wsClient) {
    override val baseUrl:String = baseUrlStub
  }

  val since = URLEncoder.encode("2017-10-10T11:10:21Z", "UTF-8")
  val until = URLEncoder.encode("2017-10-13T15:02:03Z", "UTF-8")

  val queryParam = s"?since=$since&until=$until"

  "GitHubConnector" should {
    "return repo commits in JSON format" in {
      val commitUrl = s"/repos/akka/akka/commits$queryParam"
      stubUrl(commitUrl, "test/resources/stubs/akkaCommitsResponse.json")

      val result: JsValue = await(connector.fetchCommits("2017-10-10T11:10:21Z", "2017-10-13T15:02:03Z","akka","akka"))
      result.toString().contains("chbatey") shouldBe true
    }

    "return repo releases in JSON format" in {
      val url = s"/repos/akka/akka/releases"
      stubUrl(url, "test/resources/stubs/akkaReleasesResponse.json")

      val result = await(connector.fetchReleases("akka", "akka"))
      result.toString().contains("v2.6.5") shouldBe true
    }

    "return a BAD REQUEST when sending an invalid Json" in {
      val commitUrl = s"/repos/akka/akka/commits$queryParam"

      stubUrl(commitUrl, "", 400)
      connector.fetchCommits("20170101","20180101","akka","akka").onComplete{
        case Success(_) => fail
        case Failure(e) => e.getMessage.contentEquals("BAD REQUEST") shouldBe true
      }
    }
  }
}*/
