# AccountController
This class will handle incoming HTTP requests (POST) for adding, removing and displaying an account's contacts

## Parent Class:
N/A

## Subclasses:
N/A

## Responsibilities:
- POST submitContactAddition();
	- Takes in a ContactRequest object and returns a SimpleResponse detailing whether the contact successfully added
- POST submitContactRemoval();
	- Takes in a ContactRequest object and returns a SimpleResponse detailing whether the contact was successfully removed
- GET submitContactDisplay();
	- Takes in a BasicRequest object and returns a SimpleResponse containing the list of contacts for an account

## Collaborators:
- [[AccountUseCases]]
- [[ProfileUseCases]]
- [[ContactRequest]]
- [BasicRequest]]
- [[ResponseContainer]]
- [[ShortResponse]]