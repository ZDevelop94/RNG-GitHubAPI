import org.scalatest.{OptionValues, ShouldMatchers, WordSpec}
import org.scalatestplus.play.{OneAppPerSuite, WsScalaTestClient}
import services._
import support.GitHubAPIStubs._

class releaseNoteGeneratorSpec extends WordSpec with ShouldMatchers with OptionValues with WsScalaTestClient with OneAppPerSuite{

  val RNG = new ReleaseNoteGenerator()

  "releaseNoteGenerator" should {
    "extract a list of commits" in {
      val result = RNG.messageExtractor(validGitHubResponseJson)
      result.foreach(x => x.contains("set default non-local") shouldBe true)
    }
  }
}