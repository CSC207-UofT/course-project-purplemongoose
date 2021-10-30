# kard. Progress Report Phase 0

## Work done as of Friday October 15, 2021:

- Completed [specification sheet](https://github.com/CSC207-UofT/course-project-purplemongoose/blob/main/phase0/specification.md)
- Completed [progress report](https://github.com/CSC207-UofT/course-project-purplemongoose/blob/main/phase0/README.md) (This document)
- Completed [walkthrough](https://github.com/CSC207-UofT/course-project-purplemongoose/blob/main/phase0/walkthrough.md)
- Completed initial [CRC cards](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/phase0/CRC%20Cards)
- Completed skeleton program (accessible by opening an IntelliJ project ) **DEPRECATED. NO LONGER PHASE 0 SKELETON PROGRAM**
  - Completed documentation for skeleton
  - Wrote tests for the skeleton program (Coverage currently: Code 44%, Classes 50%)

## [Specification](https://github.com/CSC207-UofT/course-project-purplemongoose/blob/main/phase0/specification.md)

The goal of kard is the move the concept of the "business card" to the 21st century. Make contact sharing painless, using methods that users are already familar with trough digital means. kard will do the following

- Store and present users, and organizations
- Provide methods of easily accessing an individual "contacts book"
- Provide methods for easly sharing contacts

## [CRC Cards](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/phase0/CRC%20Cards)

We made the CRC cards in a way such that it follows clean architecture and is easy to follow and understand. The CRC cards we have right now do not fully represent our program as the implementation details (e.g. gateway) make it quite obscure what we need. However, they do contain some important details such as our Entities (User, Client), the EntityGateway, couple controllers and the use cases they would call upon and the presenter/UI model.

A map of the CRC cards, including their clean architecture layer can be seen below:

<img src="https://github.com/CSC207-UofT/course-project-purplemongoose/blob/documentation/phase0/image_assets/CRC_Map.png" alt="CRC_Map_Cropped" style="zoom:40%;" />

A node map of all of the references between different classes is shown below:

![Node_Map](https://github.com/CSC207-UofT/course-project-purplemongoose/blob/main/phase0/image_assets/Node_Map.png)

## [Scenario Walk Through](https://github.com/CSC207-UofT/course-project-purplemongoose/blob/main/phase0/walkthrough.md)

The scenario walk through guides an implementer through the process of a new user initializing their profile and adding the business cards of the members of their own company.

The walk through begins with a new user and follows their path through classes the classes of the program

## Skeleton Program

> The program at the root directory of the git has been updated and the data below is not necessarily updated

The early implementation of Kard is currently limited to one user and one overall gateway.

It has the following functionality:

- Add user to the individuals contacts book
- Remove user from the individuals contacts book
- Display all contacts in the individuals contacts book
- Add user to the overall gateway

This functionalty has been implemented in such a way that the command line interface is secondary - it is simply the the interface for interacting with the core program.

In doing so, we have made a concious effort to adhear to "Clean Architecture" guidelines, seperating Entities, Use Cases, Controllers, and Interfaces.

> Note: Major changes to the CRC cards have been made since the latest version of the Skeleton Program, and therefore, the skeleton program does show some discrepancies from the CRC cards. These will be fixed in the next version of the program, which will be updated to better reflect the CRC cards.
>
> We made the changes to the CRC cards because we found that the previous versions lacked core components of clean architecture and wanted a more expandable, maintainable code base.

Refer to [README](https://github.com/CSC207-UofT/course-project-purplemongoose/blob/main/README.md) for detailed instructions for requirements for compiling and running kard

#### Documentation

An effort has been made to document most if not all significant methods in the program that are not simply getters and setters. This documentaon includes fulll javadocs and adheres to javadoc styling.

#### Tests

Tests have been written to ensure functionanilty of core parts of the program including but not limited to:

- Adding users to gateway
- Removing users from gateway
- Add users to contacts
- Removing users from contacts
- Controllers for adding and removing users

## Notes About Design

Given the relative simplicity of the current implementation, there is not much to note about the design of the program. 

There are, however, parts of the implementation that do need to change with further iterations of the kard program:

- The implementation of a more robust gateway for storing and fetching information
- The implementation of multiple users and the ability to share contacts fetching from the main gateway

One significant observation, however, is that due to the limited nature of the current programs implementation, it was difficult to build in the "use case" layer of  clean architecture in. The addition of this layer will be a top priority with the beginning of work on phase 1.

## Questions

A question our group is facing is how we should go about implementing the gateway. We currently have an initial concept ideas,  however, we would like some on how to effectively implement since the gateway is a critical part of the kard program. 

A second question is quite specific to the `Person` class in the program. Given that it stores a list of all people in the individuals kard stack (contacts list), should methods for interacting with the kard stack (like adding or removing contacts) be done within the entity `Person` or should they be implemented in a use case?

## Work Completed/Plans

| Member   | Contribution                                                 | Plans                                                        |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Arthur   | Early CRC Card design,<br />Manage and organize git<br />Clean up and document skeleton program<br />Write JUnit tests from skeleton program<br />Wrote project specification<br />Wrote progress report | Continue ensure quality of final product<br />User interface design and implementation<br />Documentation and tests writing |
| Ling     | Wrote initial version of the skeleton program<br />Finalized CRC Cards<br />Wrote progress report<br />Edited walkthrough | Design and implement parts of backend<br />Ensure project follows clean architecture |
| Stewart  | Cleaned up existing CRC cards and added new ones regardign gateway.<br/>Worked on the code, Refactored the command line from the Main class into a separate dedicated CommandLineInterface class, as well as tidied it up a lot. | Finalize a design structure for the data side of the project.<br/>Start writing the code for a more concrete gateway system.<br/>Write tests for more of the existing code to reach a higher level of coverage. |
| Kevin    | Edited and refined walkthrough. <br />Ensured CRC cards adhered to Clean Code and final edits<br />Refined skeleton program | Expand on skeleton program implementation with links to future gateway implementations<br />Maintain expandability of the code |
| Victoria | Created walkthrough based on CRC cards and modified CRC cards accordingly retrospectively<br/>Propose method to minimize static information and to integrate classes | Start writing methods that implement the relationships between classes<br/>Maintain structure of code to ensure opportunities for extension of new features in the future |
| Sila     | Added, designed and edited CRC Cards.                        | Add and code the missing / in-progress classes and interfaces into the program.<br /> Update CRC cards as the program is developed.<br /> |

