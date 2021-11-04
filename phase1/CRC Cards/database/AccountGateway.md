# AccountGateway
This class will interact with the SQLite API to intsert, fetch, update and remove account details from the database.

## Parent Class:
- DatabaseGateway

## Subclasses:
N/A

## Responsibilities:
- databaseConnect();
	- Returns a connection to the account databse - if the database doesn't exist, then it creates one and format the table
- createAccountTable();
	- Takes in a connection object and creates a table inside the database with specified parameters for accounts
- getAccountData();
	- Takes in the username of an account and returns back the associated account object in the database
- insertAccountData();
	- Takes in the username, password and account object and inserts it into the database as a new entry
 - updateAccountData();
	 - Takes in the username and modfiied account object and updates the existing entry in the database with the new account object.
- authAccountData();
	- Takes in the username and password and checks if there exists such an entry inside the database
## Collaborators:
N/A