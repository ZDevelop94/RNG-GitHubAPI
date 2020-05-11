package Integration

import connectors.GitHubConnectorImpl
import org.scalatest.{OptionValues, ShouldMatchers, WordSpec}
import org.scalatestplus.play.{OneAppPerSuite, WsScalaTestClient}
import play.api.libs.json.JsValue
import play.api.test.Helpers._
import play.api.libs.ws.{WSClient, _}

class GitHubConnectorImplSpec extends WordSpec with ShouldMatchers with OptionValues with WsScalaTestClient with OneAppPerSuite{

  val wsClient = app.injector.instanceOf[WSClient]
  val connector = new GitHubConnectorImpl(wsClient)

  "GitHubConnector" should {
    "return repo commits in JSON format" in {
      val result: JsValue = await(connector.getCommits("2017-10-10T11:10:21Z", "2017-10-13T15:02:03Z","hmrc","agent-subscription"))
      result.toString().contains("New endpoint") shouldBe true
    }

    "Not return repo commits not in release" in {
      val result = await(connector.getCommits("2017-10-10T11:10:21Z", "2017-10-13T15:02:03Z","hmrc", "agent-subscription"))
      result.toString().contains("fixed int spec") shouldBe false
    }

    "return repo releases in JSON format" in {
      val result = await(connector.getReleases("hmrc", "agent-subscription"))
      result.toString().contains("v0.3.0") shouldBe true
    }

    "return a BAD REQUEST when sending an invalid Json" in {
      val result = await(connector.getCommits("20170101","20180101","hmrc","agent-subscription"))
      result.toString().contains("[]") shouldBe true
    }
  }
}