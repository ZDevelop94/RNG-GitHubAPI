import org.scalatest.{OptionValues, ShouldMatchers, WordSpec}
import org.scalatestplus.play.{OneAppPerSuite, WsScalaTestClient}
import services._
import support.GitHubAPIStubs._

class releaseNoteGeneratorSpec extends WordSpec with ShouldMatchers with OptionValues with WsScalaTestClient with OneAppPerSuite{

  val RNG = new ReleaseNoteGenerator()

  "releaseNoteGenerator" should {
    "extract a list of commits" in {
      val result = RNG.messageExtractor(validCommitResponseRelease)
      result.head.contains("set default non-local") shouldBe true
    }

    "extract a list of releases" in {
      val result = RNG.getReleases(validReleasesResponse)
      result.reverse.head.contains("v0.2.0") shouldBe true
    }
  }
}