package Integration

import controllers.ReleaseNoteController
import org.scalatest.{OptionValues, ShouldMatchers, WordSpec}
import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test.{FakeRequest, _}
import services.ReleaseNoteGenerator
import connectors._
import play.api.libs.ws.WSClient
import support.GitHubAPIStubs._

class ControllerSpec extends WordSpec with ShouldMatchers with OptionValues with WsScalaTestClient with OneAppPerSuite {

  private val wsClient = app.injector.instanceOf[WSClient]
  val connector = new GitHubConnector(wsClient)
  val releaseNoteGenerator = new ReleaseNoteGenerator(connector)
  val controller = new ReleaseNoteController(releaseNoteGenerator, connector)


  "Routes" should {
    "send 404 on a bad request" in {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) shouldBe Some(NOT_FOUND)
    }
  }

  "HomeController" should {

    /*"render the index page" in {
      val home = route(app, FakeRequest(GET, "/")).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Your new application is ready.")
    }*/
  }

  "ReleaseNoteController" should {

    "return list of commits for one release" in {
      val result = controller.showCommits("agent-kyc","v0.3.0", validReleaseDates)(FakeRequest("GET", "repo/agent-kyc/v0.3.0/v0.3.0?releases=Map%28v0.2.0+->+2017-10-10T11%3A10%3A21Z%2C+v0.3.0+->+2017-10-13T15%3A02%3A03Z%29"))

      status(result) shouldBe 200
      contentAsString(result).contains("New endpoint") shouldBe true
    }

    "return list of releases" in {
      val result = controller.getReleases("agent-kyc")(FakeRequest("GET", "repo/agent-kyc"))

      status(result) shouldBe 200
      contentAsString(result).contains("v0.3.0") shouldBe true
    }
  }
}