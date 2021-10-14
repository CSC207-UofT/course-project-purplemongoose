package entity;

public abstract class User {
    public abstract boolean addContact(Entity e); // return true if added successfully
    public abstract boolean removeContact(Entity e); // return true if removed successfully
    public abstract Object getContact(); // return some sort of container for contacts

}
