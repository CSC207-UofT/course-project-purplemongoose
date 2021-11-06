## Contents

 - [Git Setup](#git-setup)
 - [Roadmap](#roadmap)
 - [Framework and Technologies](#frameworks-and-technologies)
 - [Project phases](#project-phases)

 ## Git Setup

 At the root dirctory of the Github repository there are three separate IntelliJ projects.
   - [kard-server](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard-server) - The server backend for kard. This is the core of kard
   - [kard-cli](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard-CLI) - A separate project for the command line. This project needs `kard-server` to be running as it uses it as a back end and communicates with it using HTTP requests
   - [kard_project_test](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test) - A mobile app for kard written in Flutter and Dart. This project also needs `kard-server` to be running since it relies on it as a backend and also uses HTTP to send requests.

 > Note that there is a directory called 'kard' which currently houses a work in progress re-tool or 'kard_project_test'. Please ignore it for phase 1 as it is very preliminarty. We ment to keep it on a branch, however due to a mistake in a push, it got merged to main.

 ## Roadmap

 Last updated October 21, 2021
 This project is written primarily in Java and developed in the IDE: [IntelliJ](h

 Unit tests are written with [Junit5.7](https://junit.org/junit5/) 

 We are using [SQLite](https://www.sqlite.org/index.html) to set up and manage the database.

 ## Project Phases

 Project [Phase 0](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/phase0)
 Project [Phase 1](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/phase1)

 ## Authors

- [Ling Ai](https://github.com/warzone2243)
- [Arthur Gao](https://github.com/Affixrevy)
- [Stewart Chandler](https://github.com/StewartChandler)
- [Kevin Deng](https://github.com/tiantian205)
- [Sila Taskin](https://github.com/mericsila)
- [Victoria Zhang](https://github.com/vzhang1112)
