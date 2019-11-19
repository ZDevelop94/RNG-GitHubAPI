package exceptions

import play.api.Logger

private case class Unauthorized(url: String = "unknown", message: String = s"UNAUTHORIZED EXCEPTION THROWN FROM URL:")
  extends Exception(s"$message $url") {
  Logger.error(s"$message $url")
}

private case class Forbidden(url: String = "unknown", message: String = s"FORBIDDEN EXCEPTION THROWN FROM URL:")
  extends Exception(s"$message $url") {
  Logger.error(s"$message $url")
}

private case class ServerError(url: String = "unknown", message: String = s"SERVER ERROR EXCEPTION THROWN FROM URL:")
  extends Exception(s"$message $url") {
  Logger.error(s"$message $url")
}

object HttpException {
  def apply(status: Int, url: String): Exception = {
    status match {
      case 401 => throw Unauthorized(url = url)
      case 403 => throw Forbidden(url = url)
      case 500 => throw ServerError(url = url)
    }
  }
}