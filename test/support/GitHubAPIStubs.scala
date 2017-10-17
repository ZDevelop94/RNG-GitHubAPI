package support

import play.api.libs.json.{JsValue, Json}

object GitHubAPIStubs {

  val validGitHubResponseJson:JsValue = Json.parse(
    """
      |[{
      |  "sha": "d783fcc31965e8a3d6103b98a94a182c225ead51",
      |  "commit": {
      |    "author": {
      |      "name": "PaulHodgson",
      |      "email": "paul.hodgson073@gmail.com",
      |      "date": "2017-10-09T09:14:45Z"
      |    },
      |    "committer": {
      |      "name": "rvijayan",
      |      "email": "rvijayan@users.noreply.github.com",
      |      "date": "2017-10-09T09:14:45Z"
      |    },
      |    "message": "Apb 1350 (#2)\n\n* [APB-1350][ph,rv] adding extra tests\r\n\r\n* [APB-1350][ph,rv] fixed int spec\r\n\r\n* [APB-1350][ph,rv] set default non-local auth host\r\n\r\n* [APB-1350][ph,rv] tidied test\r\n\r\n* [APB-1350][ph,rv] removed seemingly unreachable recover code in AuthActions",
      |    "tree": {
      |      "sha": "476e79a8fb89eff7372239435484ab3e4aa5179d",
      |      "url": "https://api.github.com/repos/hmrc/agent-kyc/git/trees/476e79a8fb89eff7372239435484ab3e4aa5179d"
      |    },
      |    "url": "https://api.github.com/repos/hmrc/agent-kyc/git/commits/d783fcc31965e8a3d6103b98a94a182c225ead51",
      |    "comment_count": 0
      |  },
      |  "url": "https://api.github.com/repos/hmrc/agent-kyc/commits/d783fcc31965e8a3d6103b98a94a182c225ead51",
      |  "html_url": "https://github.com/hmrc/agent-kyc/commit/d783fcc31965e8a3d6103b98a94a182c225ead51",
      |  "comments_url": "https://api.github.com/repos/hmrc/agent-kyc/commits/d783fcc31965e8a3d6103b98a94a182c225ead51/comments",
      |  "author": {
      |    "login": "PaulHodgson",
      |    "id": 15338612,
      |    "avatar_url": "https://avatars1.githubusercontent.com/u/15338612?v=4",
      |    "gravatar_id": "",
      |    "url": "https://api.github.com/users/PaulHodgson",
      |    "html_url": "https://github.com/PaulHodgson",
      |    "followers_url": "https://api.github.com/users/PaulHodgson/followers",
      |    "following_url": "https://api.github.com/users/PaulHodgson/following{/other_user}",
      |    "gists_url": "https://api.github.com/users/PaulHodgson/gists{/gist_id}",
      |    "starred_url": "https://api.github.com/users/PaulHodgson/starred{/owner}{/repo}",
      |    "subscriptions_url": "https://api.github.com/users/PaulHodgson/subscriptions",
      |    "organizations_url": "https://api.github.com/users/PaulHodgson/orgs",
      |    "repos_url": "https://api.github.com/users/PaulHodgson/repos",
      |    "events_url": "https://api.github.com/users/PaulHodgson/events{/privacy}",
      |    "received_events_url": "https://api.github.com/users/PaulHodgson/received_events",
      |    "type": "User",
      |    "site_admin": false
      |  },
      |  "committer": {
      |    "login": "rvijayan",
      |    "id": 1162138,
      |    "avatar_url": "https://avatars1.githubusercontent.com/u/1162138?v=4",
      |    "gravatar_id": "",
      |    "url": "https://api.github.com/users/rvijayan",
      |    "html_url": "https://github.com/rvijayan",
      |    "followers_url": "https://api.github.com/users/rvijayan/followers",
      |    "following_url": "https://api.github.com/users/rvijayan/following{/other_user}",
      |    "gists_url": "https://api.github.com/users/rvijayan/gists{/gist_id}",
      |    "starred_url": "https://api.github.com/users/rvijayan/starred{/owner}{/repo}",
      |    "subscriptions_url": "https://api.github.com/users/rvijayan/subscriptions",
      |    "organizations_url": "https://api.github.com/users/rvijayan/orgs",
      |    "repos_url": "https://api.github.com/users/rvijayan/repos",
      |    "events_url": "https://api.github.com/users/rvijayan/events{/privacy}",
      |    "received_events_url": "https://api.github.com/users/rvijayan/received_events",
      |    "type": "User",
      |    "site_admin": false
      |  },
      |  "parents": [{
      |    "sha": "fc14d9f420790cd08f27d8c7e305251d6336d348",
      |    "url": "https://api.github.com/repos/hmrc/agent-kyc/commits/fc14d9f420790cd08f27d8c7e305251d6336d348",
      |    "html_url": "https://github.com/hmrc/agent-kyc/commit/fc14d9f420790cd08f27d8c7e305251d6336d348"
      |  }]
      |}]
    """.stripMargin
  )
}
