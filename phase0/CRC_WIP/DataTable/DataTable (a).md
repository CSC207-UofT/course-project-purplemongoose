# DataTable\<T implements Serializable\> (a):
An abstract class for tables of Data, used to make querries/searches.
## Responsiblities:
Stores:
- `HashMap<long, T> idTable` used for mapping between ids and objects.
- `T getByID(long id)` function, that returns a T matching the Id provided.
