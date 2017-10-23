package Integration

import connectors.GitHubConnector
import org.scalatest.{OptionValues, ShouldMatchers, WordSpec}
import org.scalatestplus.play.{OneAppPerSuite, WsScalaTestClient}
import play.api.test.Helpers._
import play.api.libs.ws.{WSClient, _}

class GitHubConnectorSpec extends WordSpec with ShouldMatchers with OptionValues with WsScalaTestClient with OneAppPerSuite{

  val wsClient = app.injector.instanceOf[WSClient]
  val connector = new GitHubConnector(wsClient)

  "GitHubConnector" should {
    "return repo commits in JSON format" in {
      val result = await(connector.getCommits("2017-10-10T11:10:21Z", "2017-10-13T15:02:03Z","agent-kyc"))
      result.status shouldBe OK
      result.body.contains("New endpoint") shouldBe true
    }

    "Not return repo commits not in release" in {
      val result = await(connector.getCommits("2017-10-10T11:10:21Z", "2017-10-13T15:02:03Z","agent-kyc"))
      result.status shouldBe OK
      result.body.contains("fixed int spec") shouldBe false
    }

    "return repo releases in JSON format" in {
      val result = await(connector.getReleases("agent-kyc"))
      result.status shouldBe OK
      result.body.contains("v0.3.0") shouldBe true
    }

    "return a BAD REQUEST when sending an invalid Json" in {
      val result = await(connector.getCommits("20170101","20180101","agent-kyc"))
      result.body.contains("[]") shouldBe true
    }
  }
}
