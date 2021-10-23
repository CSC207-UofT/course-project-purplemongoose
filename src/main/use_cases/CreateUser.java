package use_cases;

import entity.Person;
import entity.PersonalUser;

import java.util.Objects;

public class CreateUser {

    public boolean create(String name, String phone, String email, String kind) {

        // kind is p if this is a PersonalUser and the first letter of the type of organization if CorporateUser.
        // right now we only have Business as a child class of Organization so only that case will be implemented.

        // a user should only be added to the database if it does not already exist
        // so this method should first check if this user is already in the db and if yes, return false
        // otherwise proceed

        if (kind.equals("p")) {
            PersonalUser pu = new PersonalUser();
            Person p = new Person(name, phone, email);
            // add to database
        } else {
            // CorporateUser cu = new CorporateUser();
            // Then we should determine what kind of organization it is
            if (kind.equals("b")) {
                // Business b = new Business(name, phone, email);
            }
            // add cases for other kinds accordingly
        }
        return true;
    }
}
