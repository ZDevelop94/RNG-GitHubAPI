package config

import com.typesafe.config.ConfigFactory

trait Config {

  val config = ConfigFactory.load()

  val baseUrl:String = config.getString("app.base_url")
  val defaultDate: String = config.getString("app.default_date")
}
