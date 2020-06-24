/*

package Integration

import java.net.URLEncoder

import services.ReleaseNoteGeneratorImpl
import controllers.ReleaseNoteController
import play.api.test.FakeRequest
import support.GitHubAPIStubs._
import play.api.test.Helpers._
import support.TestHelper
import connectors._

class ControllerSpec extends TestHelper {

  val releaseNoteGenerator = new ReleaseNoteGeneratorImpl(connector)
  val connector = new GitHubConnectorImpl(wsClient)
  val controller = new ReleaseNoteController(releaseNoteGenerator, connector)

  val since = URLEncoder.encode("2020-03-13T13:03:25Z", "UTF-8")
  val until = URLEncoder.encode("2020-04-30T08:46:48Z", "UTF-8")

  val queryParam = s"?since=$since&until=$until"

  val baseUrlStub:String = "http://localhost:8080/repos"

  "Routes" should {
    "send 404 on a bad request" in {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) shouldBe Some(NOT_FOUND)
    }
  }

  "ReleaseNoteController" should {

    "return list of commits for one release" in {
      val commitUrl = s"/repos/akka/akka/commits$queryParam"
      stubUrl(commitUrl, "test/resources/stubs/akkaCommitsResponse.json")

      val result = controller.showReleaseDetails("akka", "akka","v2.6.5", validReleaseDates)(FakeRequest("GET", "repo/agent-subscription/v0.3.0/v0.3.0?releases=Map%28v0.2.0+->+2017-10-10T11%3A10%3A21Z%2C+v0.3.0+->+2017-10-13T15%3A02%3A03Z%29"))

      status(result) shouldBe 200
      contentAsString(result).contains("ef797383732a0374179316688e54575f24faa3cd") shouldBe true
    }

    "return list of releases" in {
      val url = s"/repos/akka/akka/releases"
      stubUrl(url, "test/resources/stubs/akkaReleasesResponse.json")

      val result = controller.getReleases("akka","akka")(FakeRequest("GET", "/akka/akka"))

      status(result) shouldBe 200
      contentAsString(result).contains("v2.6.5") shouldBe true
    }
  }
}
*/
