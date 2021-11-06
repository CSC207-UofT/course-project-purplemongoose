# Account
Abstract class representing an account

## Parent Class:
N/A

## Subclasses:
- [[CorporateAccount]]
- [[PersonalAccount]]

## Responsibilities:
- addContact();
	- Add the profile to the contact list
- removeContact();
	- Remove the profilefrom  the contact list
- checkContact();
	- Return whether the profile is a contact
- getContacts();
	- Get all the profiles that are contacts
- addAffiliation();
	- Add the organization to the contact affiliations list
- removeAffiliation();
	- Remove the organization from the contact affiliations list
- getAffilations();
	- Get all the organization that are affiliations

## Collaborators:
- [[Organization]]
- [[Person]]
- [[ProfileType]]