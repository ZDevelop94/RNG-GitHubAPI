package support

import com.github.tomakehurst.wiremock.client.WireMock._

object WireMockStubs {

  def stubUrl(url: String) = {
    stubFor(get(urlEqualTo(url))
      .willReturn(aResponse()
        .withHeader("Accept", "application/vnd.github.v3+json")
        .withBody("Hello world!")));
  }
}
