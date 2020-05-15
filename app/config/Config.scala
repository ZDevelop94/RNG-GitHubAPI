package config

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

trait Config {

  val config = ConfigFactory.load()

  val baseUrl:String = config.getString("app.base_url")
  val defaultDate: String = config.getString("app.default_date")
  val authInitialisePath = config.getString("auth.new_path")
}
