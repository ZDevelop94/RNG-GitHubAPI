package controllers

import java.util.UUID

import auth.AuthState
import config.Config
import javax.inject._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller with Config {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    val state = AuthState(UUID.randomUUID().toString)
    Ok(views.html.home(state.value, clientId, redirectUri)).withSession("state" -> state.value)
  }
}
