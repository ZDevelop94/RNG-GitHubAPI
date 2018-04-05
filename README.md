# RNG-GitHubAPI
Release note Generator using the GitHub API to produce release notes. This project is in the POC stage for HMRC use

To run, do sbt run and it will start up locally on port 9000.

There are currently two endpoints you can hit:

http://localhost:9000/repo/:user/:repo

This endpoint will return an HTML view that displays a list of available release notes

http://localhost:9000/repo/:user/:repo/release/:version

This enpoint ill return an HTML view that displays the release note for that specified version
