/*
package Integration

import controllers.HomeController
import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ControllerSpec extends PlaySpec with OneAppPerTest {

  val controller = new ReleaseNoteController()

  "Routes" should {

    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }

  }

  "HomeController" should {

    "render the index page" in {
      val home = route(app, FakeRequest(GET, "/")).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Your new application is ready.")
    }

  }

  "CountController" should {


    "return an increasing count" in {
      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "0"
      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "1"
      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "2"
    }

  }

  "ReleaseNoteController" should {

    "return list of commits for one repo" in {
      val result = await(controller.showCommits(FakeRequest("GET", "repo/agent-kyc")))

      status(result) shouldBe 200
      contentAsString(result) should contain ("[APB-1350][ph,rv] added readme details, renamed end point, changed port")
    }
  }
}
*/
