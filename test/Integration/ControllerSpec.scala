package Integration

import controllers.{HomeController, ReleaseNoteController}
import org.scalatest.{OptionValues, ShouldMatchers, WordSpec}
import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test.{FakeRequest, _}
import services.ReleaseNoteGenerator
import connectors._
import play.api.libs.ws.WSClient

/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  * For more information, consult the wiki.
  */
class ControllerSpec extends WordSpec with ShouldMatchers with OptionValues with WsScalaTestClient with OneAppPerSuite {

  private val wsClient = app.injector.instanceOf[WSClient]
  val releaseNoteGenerator = new ReleaseNoteGenerator()
  val connector = new GitHubConnector(wsClient)
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

    "return list of commits for one repo" in {
      val result = controller.showCommits("agent-kyc","v0.3.0")(FakeRequest("GET", "repo/agent-kyc/v0.3.0"))

      status(result) shouldBe 200
      contentAsString(result).contains("added readme details") shouldBe true
    }

    "return list of releases" in {
      val result = controller.getReleases("agent-kyc")(FakeRequest("GET", "repo/agent-kyc"))

      status(result) shouldBe 200
      contentAsString(result).contains("v0.3.0") shouldBe true
    }
  }
}