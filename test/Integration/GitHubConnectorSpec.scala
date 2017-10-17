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
      val result = await(connector.getCommits("2017-01-01T00:00:00Z", "2018-01-01T00:00:00Z"))
      result.status shouldBe OK
      result.body.contains("[APB-1350][ph,rv] adding extra") shouldBe true
    }

    "return a BAD REQUEST when sending an invalid Json" in {
      val result = await(connector.getCommits("20170101","20180101"))
      result.body.contains("[]") shouldBe true
    }
  }
}