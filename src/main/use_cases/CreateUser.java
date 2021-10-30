package use_cases;

import entity.profiles.Person;
import entity.PersonalUser;

public class CreateUser {

    public void create(String name, String phone, String email, String kind) {

        // kind is p if this is a PersonalAccount and the first letter of the type of organization if CorporateAccount.
        // right now we only have Business as a child class of Organization so only that case will be implemented.

        // assuming this user is not already in the database

        if (kind.equals("p")) {
            PersonalUser pu = new PersonalUser();
            Person p = new Person(name, phone, email);
            // add to database
        } else {
            // CorporateAccount cu = new CorporateAccount();
            // Then we should determine what kind of organization it is
            if (kind.equals("b")) {
                // Business b = new Business(name, phone, email);
            }
            // add cases for other kinds accordingly
        }
    }
}
