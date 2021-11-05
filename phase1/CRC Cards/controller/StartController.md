# StartController
This class will handle incoming HTTP requests (POST) for login and signup 

## Parent Class:
N/A

## Subclasses:
N/A

## Responsibilities:
- POST submitLogin();
	- Takes in a StartRequest object and returns a SimpleResponse detailing if the login attempt was successful
- POST submitSignUp();
	- Takes in a StartRequest object and returns a SimpleResponse detailing if the sign up attempt was successful

## Collaborators:
- AccountUseCases
- LoginAuth
- StartRequest
- ReponseContainer
- ShortResponse

