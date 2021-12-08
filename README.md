# kard


<details>
  <summary>Contents</summary>

## Contents

 - [Git Setup](#git-setup)
 - [Deployment](#deployment)
 - [Framework and Technologies](#frameworks-and-technologies)
 - [Project phases](#project-phases)
 - [Libaries](#libaries)
 - [Authors](#authors)

</details>

## Git Setup

 At the root dirctory of the Github repository there are three separate IntelliJ projects.
   - [kard-server](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard-server) - The server backend for kard. This is the core of kard
   - [kard-cli](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard-CLI) - A separate project for the command line. This project needs `kard-server` to be running as it uses it as a back end and communicates with it using HTTP requests
   - [kard](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test) - A mobile app for kard written Dart using the Flutter framework. This project also needs `kard-server` to be running since it relies on it as a backend and also uses HTTP to send requests.

<br/>
<div align="right">
    <b><a href="#kard">↥ back to top</a></b>
</div>
<br/>

## Deployment

The most current version of [kard-server](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard-server) is deployed and accessible through the link at [cloud.arthurgao.ca:9082](cloud.arthurgao.ca:9082). Http requests can be sent to this web address and will be received and processed accordingly. The CLI and Flutter app have both been configured to use this address.

If for any reason you may need to test on a different instance of [kard-server](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard-server) you may change the:

- `private final String url` variable on line 12 of [`Request.java`](https://github.com/CSC207-UofT/course-project-purplemongoose/blob/42027fe1e96b2b37730a83ec0f52de1b6fb15b85/kard-CLI/src/main/java/Request.java#L12) in `kard-CLI/src/main/java/Request.java` if you are using the CLI to access kard.
- `static constant String address` variable on line 8 of [`user_builder.dart`](https://github.com/CSC207-UofT/course-project-purplemongoose/blob/42027fe1e96b2b37730a83ec0f52de1b6fb15b85/kard/lib/user_builder.dart#L8) in  `kard/lib/user_builder.dart` if you are using the Flutter application to access kard.

To `"localhost:8082"` and ensure to run [kard-server](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard-server) on your local machine during testing using this method.

<br/>
<div align="right">
    <b><a href="#kard">↥ back to top</a></b>
</div>
<br/>

 ## Techonologies

This server side of kard is written primarily in Java and developed in the [IntelliJ IDE](https://www.jetbrains.com/idea/) and is an implementation of a [Java Spring Boot Application](https://spring.io)

- Unit tests are written with [Junit5.7](https://junit.org/junit5/) 
- We are using [SQLite](https://www.sqlite.org/index.html) to set up and manage the database.

The front end GUI application is written in [Dart](https://dart.dev) using the [Flutter](https://flutter.dev) framework to make a cross platform accessible app.

<br/>
<div align="right">
    <b><a href="#kard">↥ back to top</a></b>
</div>
<br/>

 ## Project Phases

- Project [Phase 0](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/phase0)
- Project [Phase 1](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/phase1)
- Project [Phase 2](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/phase2)

<br/>
<div align="right">
    <b><a href="#kard">↥ back to top</a></b>
</div>

<br/>

## Libraries

This project uses the following libraries:

- [sqlite-jdbc](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc)
- [org.json](https://mvnrepository.com/artifact/org.json/json)
- [Flutter](https://flutter.dev)

<br/>
<div align="right">
    <b><a href="#kard">↥ back to top</a></b>
</div>
<br/>

 ## Authors

- [Ling Ai](https://github.com/warzone2243)
- [Arthur Gao](https://github.com/Affixrevy)
- [Stewart Chandler](https://github.com/StewartChandler)
- [Kevin Deng](https://github.com/tiantian205)
- [Victoria Zhang](https://github.com/vzhang1112)
- [Sila Taskin](https://github.com/mericsila)
