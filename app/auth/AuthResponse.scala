/*
package auth

case class AuthResponse(
                         id: Int,
                         url: String,
                         scopes: Seq[String],
                         token: String,
                         token_last_eight: String,
                         hashed_token: String,
                         app: App,
                         note: String,
                         note_url: String,
                         updated_at: String,
                         created_at: String,
                         fingerprint: String
                       )
/*
case class AuthResponse(
                         id: 1,
                         url: https://api.github.com/authorizations/1,
                         scopes: [
public_repo
],
token: abcdefgh12345678,
token_last_eight: 12345678,
hashed_token: 25f94a2a5c7fbaf499c665bc73d67c1c87e496da8985131633ee0a95819db2e8,
app: {
url: http://my-github-app.com,
name: my github app,
client_id: abcde12345fghij67890
},
note: optional note,
note_url: http://optional/note/url,
updated_at: 2011-09-06T20:39:23Z,
created_at: 2011-09-06T17:26:27Z,
fingerprint:
)
*/

private case class App (url: String, name: String, client_id: String)*/
