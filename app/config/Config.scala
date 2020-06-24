package config

import com.typesafe.config.ConfigFactory

trait Config {

  val config = ConfigFactory.load()

  val apiBaseUrl:String = config.getString("app.api_base_url")
  val baseUrl:String = config.getString("app.base_url")
  val defaultDate: String = config.getString("app.default_date")
  val authInitialisePath = config.getString("auth.new_path")

  val clientId = config.getString("auth.client_id")
  val clientSecret = config.getString("auth.client_secret")
  val redirectUri = config.getString("auth.redirect_uri")
}
