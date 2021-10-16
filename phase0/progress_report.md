# kard. Progress Report Phase 0

Work done as of Friday October 15, 2021:

- Completed specification sheet
- Completed initial CRC cards
- Completed skeleton program
  - Completed documentation for skeleton
  - Wrote tests for the skeleton program (Coverage currently 50%)



## Specification

The goal of kard is the move the concept of the "business card" to the 21st century. Make contact sharing painless, using methods that users are already familar with trough digital means. kard will do the following

- Store and present users, and organizations
- Provide methods of easily accessing an individual "contacts book"
- Provide methods for easly sharing contacts



## CRC Cards

CRC cards were grouped into two: 
- CRC cards for classes and interfaces that are present in the skeleton program
- CRC cards for classes and interfaces that are still in-progress. 

The current CRC cards may or may not represent all the responsibilities and the collaborators of the classes as of now, but will be updated and added onto as the development of the program progresses. 


## Skeleton Program

The early implementation of Kard is currently limited to one user and one overall database.

It has the following functionality:

- Add user to the individuals contacts book
- Remove user from the individuals contacts book
- Display all contacts in the individuals contacts book
- Add user to the overall database

This functionalty has been implemented in such a way that the command line interface is secondary - it is simply the the interface for interacting with the core program.

In doing so, we have made a concious effort to adhear to "Clean Architecture" guidelines, seperating Entities, Use Cases, Controllwers, and Interfaces.

#### Documentation

An effort has been made to document most if not all significant methods in the program that are not simply getters and setters. This documentaon includes fulll javadocs and adheres to javadoc styling.

#### Tests

Tests have been written to ensure functionanilty of core parts of the program including but not limited to:

- Adding users to database
- Removing users from database
- Add users to contacts
- Removing users from contacts
- Controllers for adding and removing users

## Notes About Design

Given the relative simplicity of the current implementation, there is not much to note about the design of the program. One comment is that we need to conisder the database for the program and how we will interact with it.

## Work Completed/Plans

| Member   | Contribution                                                 | Plans                                                        |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Arthur   | Early CRC Card design,<br />Manage and organize git<br />Clean up and document skeleton program<br />Write JUnit tests from skeleton program | Continue ensure quality of final product<br />User interface design an implementation<br />Documentation and tests writing |
| Ling     | Wrote initial version of the skeleton program<br />Worked on CRC cards | Design and implement parts of backend<br />Ensure project follows clean architecture |
| Stewart  |                                                              |                                                              |
| Kevin    |                                                              |                                                              |
| Victoria |                                                              |                                                              |
| Sila     | Added, designed and edited CRC Cards.                        | - Add and code the missing / in-progress classes and interfaces into the program.<br /> - Update CRC cards as the program is developed.<br />                                                              |

