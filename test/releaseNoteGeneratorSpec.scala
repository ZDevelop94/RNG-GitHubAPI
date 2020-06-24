/*
import config.Config
import org.scalatest.{OptionValues, WordSpec}
import org.scalatestplus.play.{OneAppPerSuite, WsScalaTestClient}
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.test.Helpers._
import support.GitHubAPIStubs._
import services._
import connectors._

import scala.concurrent.{Await, Future}

class releaseNoteGeneratorSpec extends WordSpec
  with OptionValues
  with WsScalaTestClient
  with OneAppPerSuite
  with MockitoSugar
  with Config {

  val connector = mock[GitHubConnector]
  val RNG = new ReleaseNoteGeneratorImpl(connector)

  val user = "testUser"
  val repo = "test"

  val sinceDate = "2017-10-10T11:10:21Z"
  val untilDate = "2017-10-13T15:02:03Z"

  when(connector.fetchReleases(user, repo)).thenReturn(Future(validReleasesResponse))
  when(connector.fetchCommits(defaultDate, untilDate, user, repo)).thenReturn(Future(validCommitResponseRelease))

  "releaseNoteGenerator" should {
    "extract a list of commits" in {
      val result = RNG.messageExtractor(validCommitResponseRelease)
      assert(result.head.contains("set default non-local"))
    }

    "extract a list of releases" in {
      val result: (List[String], Map[String,String]) = Await.result(RNG.getReleases(user, repo), 1.seconds)
      assert(result._1.reverse.head.contains("v0.2.0"))
    }

    "should find v0.3.0 release date" in {
      val (sinceDate, untilDate) = await(RNG.getReleaseDates(user,repo, "v0.3.0", validReleaseDates))
      assert(sinceDate == "2017-10-10T11:10:21Z")
      assert(untilDate == "2017-10-13T15:02:03Z")
    }

    "should find v0.3.0 release date without mapped list of dates" in {
      val (sinceDate, untilDate) = await(RNG.getReleaseDates("hmrc", "agent-subscription", "v0.3.0", None))
      assert(sinceDate == "2017-10-10T11:10:21Z")
      assert(untilDate == "2017-10-13T15:02:03Z")
    }
  }
}*/
