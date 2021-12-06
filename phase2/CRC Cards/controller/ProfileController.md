# ProfileController
This class will handle incoming HTTP requests (POST) for creating and modifying profiles

## Parent Class:
N/A

## Subclasses:
N/A

## Responsibilities:
- POST submitNewPersonalProfile();
	- Takes in a PersonalProfileRequest object and returns a JSimpleResponse detailing whether the new profile was successfully created

## Collaborators:
- [[ProfileUseCases]]
- [[PersonalProfileRequest]]
- [[ResponseContainer]]
- [[ShortResponse]]

