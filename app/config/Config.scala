package config

import com.typesafe.config.ConfigFactory

trait Config {

  val config = ConfigFactory.load()

  val baseUrl:String = config.getString("app.base_url")
  val baseUrl2:String = config.getString("app.base_url2")
  val defaultDate: String = config.getString("app.default_date")
  val authInitialisePath = config.getString("auth.new_path")
}
