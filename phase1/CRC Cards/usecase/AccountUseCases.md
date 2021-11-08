# AccountUseCases
Class containing use cases that deal with accounts

## Parent Class:
N/A

## Subclasses:
N/A

## Responsibilities:
- createNewAccount();
	- Creates a new PersonalAccount entity with the given arguments and adds it to the database via AccountGateway.
- addContact();
	- Adds the profile associated with the given contact username to the account associated with the given account username
- removeContact();
	- Remove the profile associated with the given contact username from the account associated with the given account username
- checkForContact();
	- Check if  the profile associated with the given contact username is a contact of the account associated with the given account username
- getContact();
	- Return the set of all contacts of a given account


## Collaborators:
- [[AccountGateway]]
- [[ProfileGateway]]
- [[Account]]
- [[PersonalAccount]]
- [[Person]]