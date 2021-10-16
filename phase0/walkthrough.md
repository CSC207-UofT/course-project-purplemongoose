# Walkthrough of CRC cards

## Scenario:

**A new user initializes their profile does the following actions:**

- **Add new users to their kard stack (contacts list)**
- **Find users in their kard stack**
- **Remove users from their kard stack**

## Walkthrough:

This scenario assumes that the organization that the new user joins has already been registered on kard and exists in the database `MainFrame` and can be accessed and queried by users and other organizations through `EntityGateway`.

A new user joins kard and initializes their account. In this initialization, they provide information that populates the classes associated to `Person`. Assuming they are using a CLI, their input would be passed to the controller `ParseInitUser` which processes the input and calls the use case `CreateUser` which ultimately interacts with the entity `Person` to create a new instance and with the gateway `ConstructClient` to add the new user to the database `MainFrame`.

In the process of entering their information to initialize their profile they can provide an organization that they belong to. For the purpose of this walkthrough, we will assume that the new user mentioned above has done so.

Say the new user just joined the company, they wish to add the contact information of their coworkers to their individual contacts list (kard stack). Through the CLI, their request would be passed to the controller `FetchClients` which would call the use case `SortClients` which would sort the clients fetched from `ConstructClient` which fetches from the database `MainFrame` by their organization, allowing the user to add all contacts that share an organization with themselves.

While working at the company, the user would like to find the email of their bosses boss. Again, through the CLI, their request would be passed to the use case `FetchContacts` which would call `SortContacts` to find the bosses boss which would be passed to the presenter `AppViewModel` before ultimately being displayed to the user back on the CLI

Say the user later quit from the company on bad terms and wanted to remove the contact information of their ex-coworkers. Again, through the CLI, their request would be passed to the controller `AdjustUser` which would call the use case `ModifyContacts` which would call the use case `SortContacts` to sort and fetch all contacts that worked for the users previous place of employment. `ModifyContacts` wold then be able to remove the contacts from the users kard stack (contacts list).

