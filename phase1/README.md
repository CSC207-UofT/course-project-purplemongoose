# kard Phase 1 design document

## Description of Work Done

As of November 5, much progress has been made to kard including but not limited to:

- Update of the code base initially refered to as the skeleton code to more closely adhere to guidelines set out in the updated CRC cards from phase 0.
- Update documentation to include the ability to include the idea of accounts including signing up and signing in.
- Update of the kard backend to now function purely as a [Spring]() server that accepts and sends HTTP requests to interact with the database
- Implemented multiple different UI's including:
  - Basic login and signup functionalty on a smartphone application built in Flutter and Dart. This application can be shipped on Android and iOS.
  - CLI with functionality to interact with the server backend
- Implemented classes for instantiable entities like `email` and `phone`. These will provide the user with the ability to add multiple of these attributes to their profile

## Description of Project Structure

Currently the project works in a server client manner. The servers responsibilites including doing computation and providing methods for interacting with the SQLite database.

The clients responsibility consists solely of getting the data and displaying it in a user-understanble manner.

In this way, the project largely follows guidelines set out in Clean Architecture since the UI applications function as the [presenters] while the backend contains the controllers, use cases, and entities.

The current package structure is set up for initial development to ensure that the program adheres to clean architecture with the packages being the individual layers. Doing so made it very easy to spo violations of clean architecture since the import statements clearly showed a different layer.

We should note that due to the large amount of effort initially to design our CRC cards to follow Clean Architecture closely and during the re-write, the code has been very straightforward to expand on and edit features. With the layers of clean architecture followed closely, a re-write of the controllers to allow for HTTP functionalty required no editing to the entities and use cases since they did not rely on each other. 

## Clean Architecture Layers

### Entities

The entities in kard largely consist of methods of storing and interpreting the information in the datases. The SQLite stores a 

## Descriptions of the GUI



## Some questions

For documentation, do getters and setters require javadocs



## Major Contributions to kard

