# DataManager
A controller class responsible for the managment of the DataTables.

## Responsibilities
Stores:
- A `CardDataTable cardTable` for querrying cards.
- A `PersonDataTable personTable` for querrying people.
- A `OrganizationDataTable orgTable` for querrying organizations.
- A `DataIOStream<Card> dsCard` for saving/loading card data.
- A `DataIOStream<Person> dsPerson` for saving/loading card data.
- A `DataIOStream<Organization> dsOrg` for saving/loading card data.

Functions for saving/loading/updating this data.

## Collaborators
[[CardDataTable]]
[[OrganizationDataTable]]
