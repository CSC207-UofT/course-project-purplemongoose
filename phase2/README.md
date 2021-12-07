# kard Phase 1 design document

## Contents

TODO: ARHTUR - fix

- [Summary](#description-of-work-done)
- [Navigating Github](#navigating-the-github-repository)
- [Project Structure](#description-of-project-structure)
    - [Packaging](#packaging)
- [Design and Architecture](#design-and-architecture)
    - [SOLID](#solid)
    - [Clean Architecture](#clean-architecture)
    - [Design Problems](#design-problems)
    - [Design Patterns](#design-patterns)
- [Description of the UI](#descriptions-of-the-ui)
    - [Flutter App](#flutter-app)
    - [CLI](#command-line-application)
- [Other Notes](#other-notes)
    - [Refactoring](#refactoring)
    - [Use of Github](#use-of-github-and-git)
    - [Questions](#some-questions)
- [Contributions](#major-contributions-to-kard)

## Description of Work Done

As of December 9, an initial shippable version of kard has been completed:

- Update of the phase 1 code base addressing concerns and feedback from phase 1. Detailed breakdown to the responses and changed stemming from phase 1 feedback can be found [here]()
- Update the Kard Flutter application to reach near feature parity with the command line application including
  - Signing up and creating an individual profile
  - Logging in and accessing an individuals contacts
  - Adding a new contact based on username
  - Removing a contact
- Updated the Kard Flutter application to better adhere to accessibility guidelines laid out in Googles [Material Design 2](https://material.io/design) and [Material Design 3](https://m3.material.io) guidelines.
- Added sorting to fetch order for contacts. A user can now sort their contacts alphabetically.
- Added rollback ability to individual profiles, allowing users to view and rollback changes they made to their profile like an erroneous edit.

## Navigating the Github Repository

 At the root dirctory of the Github repository there are three separate IntelliJ projects.

   - [kard-server](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard-server) - The server backend for kard. This is the core of kard
   - [kard-cli](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard-CLI) - A separate project for the command line. This project needs `kard-server` to be running as it uses it as a back end and communicates with it using HTTP requests. Note that by default, 
   - [kard](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test) - A mobile app for kard written Dart using the Flutter framework. This project also needs `kard-server` to be running since it relies on it as a backend and also uses HTTP to send requests.

## Description of Project Structure

Currently the project works in a server client manner. The servers responsibilities include doing computations and providing methods for interacting with the SQLite database.

The clients responsibility consists solely of getting the data and displaying it in a user friendly manner.

### Packaging

We ended up choosing to package by clean architecture layer as we thought that this organized our code in a way that made it easy to find as we wanted to ensure that data flow closely followed guidelines set out by clean architecture which made finding code in packages coming from layers relatively easy compared to other methods.

Another benefit of packaging by layer is that it was very easy to spot violations of clean architecture since the import statements clearly showed a different package and therefore layer was being addressed.

## Kard-Server Implementation Details

In phase 1, kards backend was modified so that instead of running a command line application, it responded to HTTP GET and POST requests. In doing so, we implemented a form of a [RESTful API](https://www.redhat.com/en/topics/api/what-is-a-rest-api) into our backend, allowing any frontend user interface, like the command line or mobile application, to be completely disconnected.

For phase 2, we have continued development down this path.....

TODO: LING add details

The implementation of the RESTful API can be observed throughout the entire app, with a specific example being if a user wishes to login. In this example the application the user is using would send a POST request to `http://host-IPv4:port/kard/login/` containing the JSON `{accountUsername: username, accountPassword: password}`. The server would then receive and process this request, returning to the user interface application another JSON containing the result of the login function specifically whether the password matches the username and the can, therefore, login.

TODO: ARTHUR expand on Flutter Application implementation

## Design and Architecture

TODO: LING update this section to reflect changes made in phase 2

### SOLID

Some of our code follow the single responsibility principle. For example `LoginAuth` only deals with authenticating the login information and returning back whether the attempt was successful for not. However, some classes might be too large and encompassing. Take `AccountUseCases` as an example; it contains methods that create Account instances while also having methods that modify the contacts in accounts. We instead should separate `AccountUseCases` into two separate classes - one that deals with the creation and deletion of Account objects (maybe called `ManageAccount`) and the other which deals with the contacts of account (`ManageContacts`). This problem was more due to issues with selecting viable names for certain classes at the beginning, so we just decided to clump everything to do with accounts under one umbrella class.

As for the other principles (SOLID), our code current does not have the depth to showcase the them well. This is because the major improvements on our program for phase 1 were mostly on the frontend side of things (Flutter GUI, CLI, REST API). As a result, the backend functionality only consists of creating an account and profile, logging in, and adding a profile to the accounts contacts list. For phase 2, we plan on adding the algorithmic brunt of our program which will be creating and editing various cards which will definitely showcase the SOLID principle much better. We also plan on adding layers of separation between the various layers to make testing easier which will also apply SOLID principles.

### Clean Architecture

We should note that due to the large amount of effort initially to design our CRC cards to follow Clean Architecture closely and during the re-write, the code has been very straightforward to expand on and edit features. With the Dependency Rule being followed for every layer, a re-write of the controllers to allow for HTTP functionality required no editing to the entities and use cases since they did not rely on each other. 

To see how we have applied clean architecture visually, we have included an annotated UML diagram where the layers are marked out. As seen, there are clear separation between the 4 layers, and the dependency goes in one direction. Also, notice there are classes that are not included in any of the layers. These classes serve more of a helper functionality to the controllers as all they are designed to do is interpret JSON files and store HTTP responses, which are all handled with the controllers. 

![Kard phase 1 UML](README.assets/Kard%20phase%201%20UML.png)
- Scenario Walkthrough
Suppose the GUI sends a request for a new account to be made. `submitSignUp` from `StartController` would then receive that request and call `createNewAccount` from `AccountUseCases` to create an account. `createAccount` then instantiates a new `PersonalAccount` object and calls the insertAccountData from `AccountGateway` to add it to the database along with the username and password sent over with the request.

The Dependency Rule states that source code dependencies should only point outwards. An example of this would be the `ProfileUseCases` class. It imports `ProfileGateway` and various entity classes but does not import from the controllers thus keeping in line with the Dependency Rule.

### Design Problems

TODO: ARTHUR

### Design Patterns

TODO: LING&ARTHUR

## Other Notes

### Refactoring

Refactoring has continued to be a critical part of maintaining our code and ensuring expandability and allowing further development. The code is continuously refactored to keep up with new code and to update it to follow the most recent structures--however--there are a few occasions where specific, and large refactors can be observed.

*Note that some of these examples are from phase 1*

Some specific examples where refactors have happened:

- We moved the main project from the root directory of the git to [kard-server](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard-server). See [pull request 39](https://github.com/CSC207-UofT/course-project-purplemongoose/pull/39).
- We changed the name of a 'user' to 'account' around the program. We thought that the new name better represented the function of an 'account' in kard and allowed us to differentiate it from a profile. A profile stores the personal information of a user while an account stores the profile of the user and the users' contacts list.
    - This refactor happened through different pull requests so specifice commits have been provided
    - [Initial file change](https://github.com/CSC207-UofT/course-project-purplemongoose/commit/f09820649ae6769df60e03e69802722309805815) This is when we initially changed the file and class name.
    - [Changes around kard](https://github.com/CSC207-UofT/course-project-purplemongoose/pull/25/commits/4dcb2b6b30ba0ae5948fa9a7bc372f2f37f49833) This is when code was changed to reflect the new file and class name
    - [Some general restructuring](https://github.com/CSC207-UofT/course-project-purplemongoose/pull/22) These were largely to clean up old code and to prepare for the restructure.
    

TODO: LING pick out some more pull requests or commits detailing refactors made to kard

### Use of Github and Git

Git has been used extensively to manage and facilitate the development of kard. We constantly use branches whenever new  features are being added to stage them and test before merging to main. Minimal committing to main have been made as we kept main as a fully working and tested version of the code. 

There has been limited usage of Github issues and actions - for issues in the code, we have used our Discord Server to communicate. As for actions, we plan on implementing some for phase 2 to ensure that there is some automatic checking of files that we push.

TODO: ARTHUR - implement GitHub actions

## Major Contributions to kard

| Name     | Responsibilities                                             |
| -------- | ------------------------------------------------------------ |
| Arthur   | Develop Flutter GUI<br />Maintain Git and manage merge requests<br />Coordinate group meetings and roadmap<br />Design Documentation |
| Ling     | Rewrite of significant chunks of kard-server to better adhere to clean architecture and SOLID<br />Updated tests for kard-server<br />Maintain and ensure functionality of backend |
| Victoria | Develop entities for the program<br />Write tests and documentation for entities<br />Authored accessibility documentation |
| Stewart  | Develop the backend database infastructure<br />Write tests for kard server |
| Kevin    | Develop and maintain CLI through different iterations of the backend<br />Implemented memento design pattern for user profile editing |
| Sila     | Write tests for kard server                                  |

