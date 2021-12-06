# ProfileUseCases
Class containing use cases that deal with profiles

## Parent Class:
N/A

## Subclasses:
N/A

## Responsibilities:
- createNewPerson();
	- Creates a new person object with the given parameters and adds it to the database via ProfileGateway
- checkForProfile();
	- Check if there is already a profile associated with the given account username in the database

## Collaborators:
- [[ProfileGateway]]
- [[Email]]
- [[Name]]
- [[Phone]]
- [[Organization]]
- [[Person]]