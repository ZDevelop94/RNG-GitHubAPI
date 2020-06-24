package support

import play.api.libs.json.{JsValue, Json}

object GitHubAPIStubs {

  val validReleasesResponse = Json.parse(
    """
      |[
      |    {
      |        "url": "https://api.github.com/repos/hmrc/agent-subscription/releases/8112015",
      |        "assets_url": "https://api.github.com/repos/hmrc/agent-subscription/releases/8112015/assets",
      |        "upload_url": "https://uploads.github.com/repos/hmrc/agent-subscription/releases/8112015/assets{?name,label}",
      |        "html_url": "https://github.com/hmrc/agent-subscription/releases/tag/v0.3.0",
      |        "id": 8112015,
      |        "tag_name": "v0.3.0",
      |        "target_commitish": "master",
      |        "name": "0.3.0",
      |        "draft": false,
      |        "author": {
      |            "login": "hmrc-web-operations",
      |            "id": 6948709,
      |            "avatar_url": "https://avatars0.githubusercontent.com/u/6948709?v=4",
      |            "gravatar_id": "",
      |            "url": "https://api.github.com/users/hmrc-web-operations",
      |            "html_url": "https://github.com/hmrc-web-operations",
      |            "followers_url": "https://api.github.com/users/hmrc-web-operations/followers",
      |            "following_url": "https://api.github.com/users/hmrc-web-operations/following{/other_user}",
      |            "gists_url": "https://api.github.com/users/hmrc-web-operations/gists{/gist_id}",
      |            "starred_url": "https://api.github.com/users/hmrc-web-operations/starred{/owner}{/repo}",
      |            "subscriptions_url": "https://api.github.com/users/hmrc-web-operations/subscriptions",
      |            "organizations_url": "https://api.github.com/users/hmrc-web-operations/orgs",
      |            "repos_url": "https://api.github.com/users/hmrc-web-operations/repos",
      |            "events_url": "https://api.github.com/users/hmrc-web-operations/events{/privacy}",
      |            "received_events_url": "https://api.github.com/users/hmrc-web-operations/received_events",
      |            "type": "User",
      |            "site_admin": false
      |        },
      |        "prerelease": false,
      |        "created_at": "2017-10-13T15:02:03Z",
      |        "published_at": "2017-10-13T15:02:05Z",
      |        "assets": [],
      |        "tarball_url": "https://api.github.com/repos/hmrc/agent-subscription/tarball/v0.3.0",
      |        "zipball_url": "https://api.github.com/repos/hmrc/agent-subscription/zipball/v0.3.0",
      |        "body": "\nRelease            : agent-subscription 0.3.0\nRelease candidate  : agent-subscription 0.2.0-4-gbfd1862\n\nLast commit sha    : bfd186256868ea0f582a848f04f74edd2ae285da\nLast commit author : GitHub\nLast commit time   : 13 October 2017 14:22:30 +00:00\n\nRelease and tag created by [Releaser](https://github.com/hmrc/releaser) 1.6.0"
      |    },
      |    {
      |        "url": "https://api.github.com/repos/hmrc/agent-subscription/releases/8060680",
      |        "assets_url": "https://api.github.com/repos/hmrc/agent-subscription/releases/8060680/assets",
      |        "upload_url": "https://uploads.github.com/repos/hmrc/agent-subscription/releases/8060680/assets{?name,label}",
      |        "html_url": "https://github.com/hmrc/agent-subscription/releases/tag/v0.2.0",
      |        "id": 8060680,
      |        "tag_name": "v0.2.0",
      |        "target_commitish": "master",
      |        "name": "0.2.0",
      |        "draft": false,
      |        "author": {
      |            "login": "hmrc-web-operations",
      |            "id": 6948709,
      |            "avatar_url": "https://avatars0.githubusercontent.com/u/6948709?v=4",
      |            "gravatar_id": "",
      |            "url": "https://api.github.com/users/hmrc-web-operations",
      |            "html_url": "https://github.com/hmrc-web-operations",
      |            "followers_url": "https://api.github.com/users/hmrc-web-operations/followers",
      |            "following_url": "https://api.github.com/users/hmrc-web-operations/following{/other_user}",
      |            "gists_url": "https://api.github.com/users/hmrc-web-operations/gists{/gist_id}",
      |            "starred_url": "https://api.github.com/users/hmrc-web-operations/starred{/owner}{/repo}",
      |            "subscriptions_url": "https://api.github.com/users/hmrc-web-operations/subscriptions",
      |            "organizations_url": "https://api.github.com/users/hmrc-web-operations/orgs",
      |            "repos_url": "https://api.github.com/users/hmrc-web-operations/repos",
      |            "events_url": "https://api.github.com/users/hmrc-web-operations/events{/privacy}",
      |            "received_events_url": "https://api.github.com/users/hmrc-web-operations/received_events",
      |            "type": "User",
      |            "site_admin": false
      |        },
      |        "prerelease": false,
      |        "created_at": "2017-10-10T11:10:21Z",
      |        "published_at": "2017-10-10T11:10:23Z",
      |        "assets": [],
      |        "tarball_url": "https://api.github.com/repos/hmrc/agent-subscription/tarball/v0.2.0",
      |        "zipball_url": "https://api.github.com/repos/hmrc/agent-subscription/zipball/v0.2.0",
      |        "body": "\nRelease            : agent-subscription 0.2.0\nRelease candidate  : agent-subscription 0.1.0-8-gd783fcc\n\nLast commit sha    : d783fcc31965e8a3d6103b98a94a182c225ead51\nLast commit author : rvijayan\nLast commit time   : 09 October 2017 09:14:45 +00:00\n\nRelease and tag created by [Releaser](https://github.com/hmrc/releaser) 1.6.0"
      |    }
      |]
    """.stripMargin
  )

  //manually reversed as getRelease reverses lists
  val validReleaseDates = Some(Map(
    "v2.6.4" -> "2020-03-13T13:03:25Z",
    "v2.6.5" -> "2020-04-30T08:46:48Z"))
}