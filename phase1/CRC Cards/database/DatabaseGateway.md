# DatabaseGateway
Abstract  class for AccountGateway and ProfileGateway

## Parent Class:
N/A

## Subclasses:
- AccountGateway
- ProfileGateway

## Responsibilities:
- databaseConnect();
	- Abstract method for connecting to the database
- toBytes();
	- Serializes an object into a byte array for storage inside a database
- toObject();
	- Deserializes an object from a byte array into an object

## Collaborators:
N/A