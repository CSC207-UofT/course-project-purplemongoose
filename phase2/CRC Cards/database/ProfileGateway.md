# ProfileGateway
This class will interact with the SQLite API to intsert, fetch, update and remove profile details from the database.

## Parent Class:
- [[DatabaseGateway]]

## Subclasses:
N/A

## Responsibilities:
- databaseConnect();
	- Returns a connection to the account databse - if the database doesn't exist, then it creates one and format the table
- createProfileTable();
	- Takes in a connection object and creates a table inside the database with specified parameter for profiles
- getProfileData();
	- Takes in the username of a profile and returns back the associated profile object in the database
- insertProfileData();
	- Takes in the username and profile object and inserts it into the database as a new entry
 - updateProfileData();
	 - Takes in the username and modified profile object and updates the existing entry in the database with the new profile object
	 
## Collaborators:
N/A