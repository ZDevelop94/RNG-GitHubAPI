package auth

case class OAuthToken(access_token: String, scope: String, token_type: String)

case class AuthState(value: String)