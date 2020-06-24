package controllers

import auth.AuthState
import connectors.AuthConnector
import javax.inject.Inject
import play.api.mvc.{Action, AnyContent, Controller, Request}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AuthController @Inject()(authConnector: AuthConnector) extends Controller {
  def authenticate = Action.async { request: Request[AnyContent] =>
    val gitCode: Option[String] = request.getQueryString("code")
    val state: Option[String] = request.session.get("state")

    (gitCode, state) match {
          case (Some(code), Some(stateValue)) => implicit val s = AuthState(stateValue)
            authConnector.authExchange(code).flatMap {
                case Right(token) =>
                  Future(Redirect(
                    routes
                      .ReleaseNoteController
                      .getReleases("ZDevelop94", "RNG-GitHubAPI"))
                    .withSession("OAuth" -> token.access_token))
                case Left(e) => println(s"Failure!! Cause: ${e.getCause}")
                  Future(Redirect(routes.HomeController.index()))
            }
          case (Some(_), None) => println("State not found from session, REDIRECTING To Home page")
            Future(Redirect(routes.HomeController.index()))
          case (None, Some(_)) => println("code not included in query string, REDIRECTING To Home page")
            Future(Redirect(routes.HomeController.index()))
    }
  }
}