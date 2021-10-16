# SortContacts
A class for sorting the contacts of a user.

### Clean Architecture Layer
Application Business Rule [use case]

## Responsibilities
- Sort contacts by Name (A-Z)
- Sort contacts by Surname (A-Z) (if a contact is a [[Company]], the company's name will be used)
- Sort by Company (A-Z)

## Collaborators
- [[Client (I)]]
- [[User (A)]]
- [[Person]]
- [[Company]]