package entity.profiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import entity.datafiles.Phone;
import entity.datafiles.Name;
import entity.datafiles.Email;
/**
 * A class for testing the PersonMemento class
 *
 * @see PersonMemento
 */

public class PersonMementoTest {
    PersonMemento personMemento;
    Phone phone;
    Email email;
    Name name;

    @BeforeEach
    void setUp() {

        phone = new Phone("6475552401");
        email = new Email("jon666@gmail.com");
        name = new Name("George", "Michael", "she/her", "Dr.");
        this.personMemento = new PersonMemento(name, phone, email, "george2");
    }

    /**
     * Tests getting the name of a PersonMemento
     */
    @Test
    @DisplayName("Get name of PersonMemento")
    void testGetName(){
        String result = personMemento.getName();
        String expected = "Dr. George Michael";
        assertEquals(result, expected);
    }

    /**
     * Tests getting the pronouns of a PersonMemento
     */

    @Test
    @DisplayName("Get pronouns of PersonMemento")
    void testGetPronouns(){
        String result = personMemento.getPronouns();
        String expected = "she/her";
        assertEquals(result, expected);
    }

    /**
     * Tests getting the original name of a PersonMemento
     */

    @Test
    @DisplayName("Get original name of PersonMemento")
    void testGetOriginalName(){
        Name result = personMemento.getOriginalName();
        Name expected = this.name;
        assertEquals(result, expected);
    }

    /**
     * Tests getting the phone of a PersonMemento
     */

    @Test
    @DisplayName("Get phone of PersonMemento")
    void testGetPhone(){
        String result = personMemento.getPhone();
        String expected = "6475552401";
        assertEquals(result, expected);
    }

    /**
     * Tests getting the email of a PersonMemento
     */

    @Test
    @DisplayName("Get email of PersonMemento")
    void testGetEmail(){
        String result = personMemento.getEmail();
        String expected = "jon666@gmail.com";
        assertEquals(result, expected);
    }

    /**
     * Tests getting the username of a PersonMemento
     */

    @Test
    @DisplayName("Get username of PersonMemento")
    void testGetUsername(){
        String result = personMemento.getUsername();
        String expected = "george2";
        assertEquals(result, expected);
    }
}
