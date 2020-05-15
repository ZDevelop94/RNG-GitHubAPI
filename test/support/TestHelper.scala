package support

import org.scalatest.{BeforeAndAfterEach, Matchers, ShouldMatchers, WordSpec}
import org.scalatestplus.play.{OneAppPerSuite, WsScalaTestClient}
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.{aResponse, get, stubFor, urlEqualTo}
import play.api.libs.ws.WSClient

trait TestHelper extends WordSpec with ShouldMatchers with WsScalaTestClient with OneAppPerSuite with BeforeAndAfterEach with Matchers {

  val wsClient = app.injector.instanceOf[WSClient]
  val wireMockServer = new WireMockServer(8080)

  wireMockServer.start()

  override def afterEach(): Unit = {
    wireMockServer.resetAll()
  }

  def stubUrl(url: String, path: String, status: Int = 200) = {
    stubFor(get(urlEqualTo(url))
      .willReturn(aResponse()
        .withStatus(status)
        .withHeader("Accept", "application/vnd.github.v3+json")
        .withBody(loadFile(path))))
  }

  def loadFile(uri: String): String = {
    if(uri.nonEmpty) {
      val file = scala.io.Source.fromFile(uri)
      try file.mkString finally file.close()
    } else {
      ""
    }
  }
}
