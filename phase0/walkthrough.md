# Walkthrough of CRC cards

## Scenario:

**A new user initializes their profile and adds the cards of the members
of their own company**

## Walkthrough:

This scenario assumes that the organization that the new user joins has already
been registered on Kard and has been indexed by a `DataTable (a)` to be 
accessed and queried by users and other organizations. A new user, Person A, 
joins Kard and initializes their account. In this initialization, they provide 
information that populates the classes associated to `Person`, which is then 
used to create a `BusinessCard` for them to share and to use. This 
`BusinessCard` is then added to a `DataTable (a)` that indexes them with a 
unique ID, which allows Person A to be queried by companies and other users 
based on the unique ID and Person A's name. All of their information will be
stored as a `User`, which will store all the connections that Person A makes
on Kard.

Person A can access information about the `Organization` that they are part of 
in two ways; they can either query the `Organization` through the 
`OrganizationDataTable` or they can directly access the `Organization` through
their own account. From there, Person A will be able to browse through a list of
other employees stored under `Organization`, or they can search through the
employees list through the `PersonDataTable`. With the `User` class, Person A
will be able to add the other members of their company to their contacts list. 
Additionally, the `User` class will store Person A's contacts and fetch them
when called upon. 