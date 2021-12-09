# kard Phase 2 design document

## Contents

TODO: ARHTUR - fix

- [kard Phase 2 design document](#kard-phase-2-design-document)
  - [Contents](#contents)
  - [Description of Work Done](#description-of-work-done)
  - [Navigating the Github Repository](#navigating-the-github-repository)
  - [Description of Project Structure](#description-of-project-structure)
    - [Packaging](#packaging)
  - [Kard-Server Implementation Details](#kard-server-implementation-details)
  - [Design and Architecture](#design-and-architecture)
    - [Clean Architecture](#clean-architecture)
      - [Scenario Walkthrough](#scenario-walkthrough)
    - [Areas of Improvement](#areas-of-improvement)
    - [Design Patterns](#design-patterns)
  - [Other Notes](#other-notes)
    - [Refactoring](#refactoring)
    - [Use of Github and Git](#use-of-github-and-git)
    - [Testing](#testing)
  - [Major Contributions to kard](#major-contributions-to-kard)

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
- Massive overhaul of the SQLite database to allow for more flexibility.

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

For phase 2, we have continued development down this path by further developing the controllers to adhere to REST. In phase 1, some of the controllers used did not GET and POST properly. GET meant for viewing something in the backend while POST is used to change some values in the backend. In phase 2, GET and POST are used in proper places as opposed to phase 1 where GET was mainly used (improperly).

The implementation of the RESTful API can be observed throughout the entire app, with a specific example being if a user wishes to login. In this example the application the user is using would send a POST request to `http://host-IPv4:port/kard/login/` containing the JSON `{accountUsername: username, accountPassword: password}`. The server would then receive and process this request, returning to the user interface application another JSON containing the result of the login function specifically whether the password matches the username and the can, therefore, login.

The benefit following RESTful API is that it allows for one centralized backend (and database) for the frontend to communicate with. If each frontend were to have its own backend that it communicate directly with, it would be difficult to synchonize account data and profiles without needing alot of network related code or switching to a server based database like MySQL or MongoDB. Using HTTP requests has allowed us to create a highly customizeable database with SQLite while avoiding the complications of signing up and connecting to a server database.

TODO: ARTHUR expand on Flutter Application implementation & maybe talk about Docker and hosting

## Design and Architecture

As we've pointed out in the design document for phase 1, the OLID principle were lacking in the previous iteration of our project. However for phase 2, we made it our goal to ensure these principle are present.

**S:** We used to bundle all account related use cases in one mega class called `AccountUseCases` and that same pattern was followed for other use cases. In phase 2 we removed these huge use case classes and separated them out into smaller classes which have specific roles (e.g. `CreateAccount` only handles account creation). Additionally, the controllers only are only responsibile for one branch of requests. For example the `ContactController` only contains methods that act on contacts, those being `submitContactAddition`, `submitContactRemoval`, `submitContactDisplay`. By separating the controllers into their respective role, the URL for the controllers becomes quite easy for frontend applications to use. If the Flutter app wanted to display the contacts, it would just send a request to `http://host-url:8082/contact/display`.

**O:** Although Open-Closed Principle is used in various sections of our program, it is most commonly seen in entities as they lay the foundation of our program. For example, in our profile entities, we have `ProfileType` as  the interface which all profile classes must implemenent. Due to time constraints, we only have `Person` as the profile class, but if given more time, we could have implemented classes like `Company` which could be a parent class of `Business`, and so forth. Our memento classes also implement `ProfileType`; without having to modify the subclasses of `ProfileType` to support mementos, we have a class like `PersonMemento` which extends `Memento` which implements `ProfileType` instead. As a result, our entities are open for extension, but closed for modification.

**L:** While our entities are a good showcase of Liskov Subsitution Principle, our current iteration of the program only has one subclass of each entity interface superclass. However in our Strategy design pattern implementation of contact sorting, Liskov Subsitution principle is present. The interface `SortBehaviour` has `SortByName` as an implementor. The `sort` method for `SortByName` and other possible subclasses (e.g. `SortByTimeStamp`) will only ever take in a array of `ProfileType` objects and the order in which to sort it (`"ascend"`, `"descend"`). As such `.sort()` of all the subclasses of `SortBehaviour` can be used wherever `SortBehaviour.sort()` is used.

**I:** An example the Interface Segregation Principle can be found in `PersonalAccount` which implements `Account`. `Account` contains the following methods: `addContact`, `removeContact`, `checkContact` and `getContacts`. In `PersonalAccount` all of these methods are implemented and make sense contextually (as opposed to something like a square class implementing a shape interface with a volume method) All the subclasses that are implementing an interface support the methods from the superclass that are being inherited. There are no errors in Intelliji about subclasses not implementing superclass methods so all the subclasses that are implementing an interface support the methods from the superclass being inherited.

**D:** The Dependency Inversion Principle is used in our project, but is best demonstrated in our database implementation. Many if not all of our use cases rely on accessing the database in some way. Originally,
the database classes which directly interacted with the SQLite wrapper class was coupled to the use cases. This meant testing was impossible to do with use cases and more importantly, broke the Dependency Inversion Principle as the high level modules (use cases) depended on low level modules (database wrapper). In phase 2, we added a layer of separation by introducing `SQLiteDataBaseHelper` and `DataBaseGateway`. `SQLiteDataBaseHelper` provides an interface to the database for the gateways while the gateways obfuscate the database implementation for the use cases by providing a high level layer of interaction. As a result, the use cases now depend on `DataBaseGateway` which itself depends `SQLiteDataBaseHelper`. Instead of depending on concretions, the use cases now depend on abstraction and thus follows the Dependency Inversion Principle.

### Clean Architecture

We should note that due to the large amount of effort initially to design our CRC cards to follow Clean Architecture closely and during the re-write, the code has been very straightforward to expand on and edit features. With the Dependency Rule being followed for every layer, a re-write of the controllers to allow for HTTP functionality required no editing to the entities and use cases since they did not rely on each other. 

To see how we have applied Clean Architecture visually, we have included an annotated UML diagram where the layers are marked out. As seen, there are clear separation between the 4 layers, and the dependency goes in one direction. Also, notice there are classes that are not included in any of the layers. These classes serve more of a helper functionality to the controllers as all they are designed to do is interpret JSON files and store HTTP responses, which are all handled with the controllers. 

![Kard phase 1 UML](README.assets/uml%20diagram%20phase%202.png)

#### Scenario Walkthrough

Suppose the GUI sends a request for a new account to be made. `submitSignUp` from `StartController` would then receive that request and call `createNewAccount` from `AccountUseCases` to create an account. `createAccount` then instantiates a new `PersonalAccount` object and calls the insertAccountData from `AccountGateway` to add it to the database along with the username and password sent over with the request.

The Dependency Rule states that source code dependencies should only point outwards. An example of this would be the `ProfileUseCases` class. It imports `ProfileGateway` and various entity classes but does not import from the controllers thus keeping in line with the Dependency Rule.

### Areas of Improvement

TODO: ARTHUR

### Design Patterns

There are 3 main design patterns used in our project:

- **Command**
  - TODO: STEWART
- **Memento**
  - The Memento Design Pattern is used to create the functionality that allows the user to restore his/her profile to any previous state. 
  - This design pattern is implemented using classes including: `Memento`, `PersonMemento`, `MementoManager`, and `RestoreProfile`. 
  - The `Memento` and `PersonMemento` classes are implemented similar to `ProfileType`and `Person` classes in the entities, as they are essentially copies for each profile. 
  - We implemented a seperate caretaker class called `MementoManager`. This is where the past editions of profiles, or mementos, are managed. The `MementoManager` has an instance variable of a `LinkedHashMap` called `history` that stores all the mementos from past edits. This class also contains methods like get or add mementos, as well as a getter for the entire history. 
  - To facilitate storage of the edit history, we created a new column in our profile database that is dedicated to storing the `MementoManager` for each user. Each `Memento` and `MementoManager` objects are implemented as serializables, so we store a single serialized `MementoManager` object for each user in our database. 
  - The design pattern is used as follows in a real life scenario:
    1. The user creates a profile for the first time. This profile is then used to create a `PersonMemento`, along with a `MementoManager`. This `PersonMemento` is added to `MementoManager` history. 
    2. For each edit of the profile, a new `PersonMemento` is created and added to `MementoManager`. 
    3. When the user wants to restore the profile to a previous state, the `MementoManager` returns an array of all of the `PersonMemento` objects, representing the state of the profile after each past edit. 
    4. The user then sees the array, and selects the index of the past profile he/she wants to return to. 
    5. With the index inputted, the `MementoManager` restores the profile. 
  - Between our group, we discussed a potential limit on the number of past profiles we would keep, but since there is no obvious downside to storing the entire history, given our program's low usage, we decided that the `ementoManager` would store ALL past profiles with no limits. 
- **Strategy**
  - The Strategy Design Pattern is used to implement sorting of an account's contact list for displaying.
  - This design pattern is implemented using two classes: `SortBehavior` which is an interface, and `SortByName` which implements `SortBehavior`. `SortByName` inherits the `sort` method from `SortBehavior` but its algorithm is tailored to sort by the contact's name.
  - `ListContact` has a instance variable `sorter` of type `SortBehavior` and there is a method called `setSorter` which takes in subclasses of `SortBehavior` and sets `sorter` to that object. Now when `ListContact` calls `getSortedContacts`, `sorter.sort()` will sort the contacts based on the `SortBehavior` subclass we chose. (e.g. `SortByName`)

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

### Testing

We managed to test the entire entity and usecase directory with almost complete coverage. This, in turn, tests for a significant chunk of our project. In addition, by testing usecases, we are also implicitly testing for our gateways since as illustrated on our UML diagram, our usecases heavily depend on our gateways for communication with the database. So by testing usecases, we too tested for gateways.

We chose to not write tests for controllers as they are written in a way to receive and handle HTTP POST and GET requests, so it makes little sense to test them in the same project.

We also didn't implement tests for our Flutter application and our CommandLineInterface as they are UIs, which are hard to test with actual code so we just proved that they work with our presentation and actual uses of our application.

As of writing, our testing coverage is:

![Testing Coverage](README.assets/coverage.png)

## Major Contributions to kard

| Name     | Responsibilities                                             |
| -------- | ------------------------------------------------------------ |
| Arthur   | Develop Flutter GUI<br />Maintain Git and manage merge requests<br />Coordinate group meetings and roadmap<br />Design Documentation |
| Ling     | Rewrite of significant chunks of kard-server to better adhere to clean architecture and SOLID<br />Updated tests for kard-server<br />Maintain and ensure functionality of backend |
| Victoria | Develop entities for the program<br />Write tests and documentation for entities<br />Authored accessibility documentation |
| Stewart  | Develop the backend database infastructure<br />Write tests for kard server |
| Kevin    | Develop and maintain CLI through different iterations of the backend<br />Implemented the edit profile functionality<br />Implemented memento design pattern for user profile editing<br />Wrote tests for use case classes and entity classes|
| Sila     | Write tests for kard server                                  |

