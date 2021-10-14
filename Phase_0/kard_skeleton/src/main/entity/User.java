package main.entity;

public interface User {
    boolean addContact(Entity e); // return true if added successfully
    boolean removeContact(Entity e); // return true if removed successfully
    Object getContact(); // return some sort of container for contacts

}
