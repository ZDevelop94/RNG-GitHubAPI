
package converters

import auth.OAuthToken
import play.api.libs.json.Json

trait JsonConverters {
  implicit val reads = Json.reads[OAuthToken]
}

