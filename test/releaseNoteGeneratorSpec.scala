import org.scalatest.{OptionValues, ShouldMatchers, WordSpec}
import org.scalatestplus.play.{OneAppPerSuite, WsScalaTestClient}
import services._
import support.GitHubAPIStubs._
import connectors._
import play.api.libs.ws.WSClient
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.test.Helpers._

class releaseNoteGeneratorSpec extends WordSpec with ShouldMatchers with OptionValues with WsScalaTestClient with OneAppPerSuite{

  private val wsClient = app.injector.instanceOf[WSClient]
  val connector = new GitHubConnector(wsClient)
  val RNG = new ReleaseNoteGenerator(connector)

  "releaseNoteGenerator" should {
    "extract a list of commits" in {
      val result = RNG.messageExtractor(validCommitResponseRelease)
      result.head.contains("set default non-local") shouldBe true
    }

    "extract a list of releases" in {
      val result: (List[String], Map[String,String]) = RNG.getReleases(validReleasesResponse)
      result._1.reverse.head.contains("v0.2.0") shouldBe true
    }

    "should find v0.3.0 release date" in {
      val (sinceDate, untilDate) = await(RNG.getReleaseDates("agent-kyc", "v0.3.0", validReleaseDates))
      sinceDate shouldBe "2017-10-10T11:10:21Z"
      untilDate shouldBe "2017-10-13T15:02:03Z"
    }

    "should find v0.3.0 release date without mapped list of dates" in {
      val (sinceDate, untilDate) = await(RNG.getReleaseDates("agent-kyc", "v0.3.0", None))
      sinceDate shouldBe "2017-10-10T11:10:21Z"
      untilDate shouldBe "2017-10-13T15:02:03Z"
    }
  }
}