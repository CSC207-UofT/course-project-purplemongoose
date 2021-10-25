package use_cases;

import entity.Person;
import entity.PersonalUser;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ModifyContactsTest {
    private final PersonalUser user = new PersonalUser();
    private final Person p = new Person("Name", "1234567890", "name@name.com");
    private final ModifyContacts m = new ModifyContacts();

    @Test
    void addContact(){
        m.add(user, p);
        String contact = user.getContact().toString();
        String expected = "Name | 1234567890 | name@name.com\n";
        assertEquals(expected, contact);
    }

    @Test
    void removeContact(){
        m.remove(user, p);
        String contact = user.getContact().toString();
        String expected = "your contacts list is empty!";
        assertEquals(expected, contact);
    }
}
