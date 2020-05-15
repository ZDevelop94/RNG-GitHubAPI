package exceptions

import play.api.Logger

case class BadRequest(url: String = "unknown", message: String = s"BAD REQUEST EXCEPTION THROWN FROM URL:")
  extends Exception(s"$message $url") {
  Logger.warn(s"$message $url")
}

case class Unauthorized(url: String = "unknown", message: String = s"UNAUTHORIZED EXCEPTION THROWN FROM URL:")
  extends Exception(s"$message $url") {
  Logger.warn(s"$message $url")
}

case class Forbidden(url: String = "unknown", message: String = s"FORBIDDEN EXCEPTION THROWN FROM URL:")
  extends Exception(s"$message $url") {
  Logger.warn(s"$message $url")
}

case class ServerError(url: String = "unknown", message: String = s"SERVER ERROR EXCEPTION THROWN FROM URL:")
  extends Exception(s"$message $url") {
  Logger.warn(s"$message $url")
}

object HttpException {
  def apply(status: Int, url: String): Exception = {
    status match {
      case 400 => BadRequest(url = url)
      case 401 => Unauthorized(url = url)
      case 403 => Forbidden(url = url)
      case 500 => ServerError(url = url)
    }
  }
}