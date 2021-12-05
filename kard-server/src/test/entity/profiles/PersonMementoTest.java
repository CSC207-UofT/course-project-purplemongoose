package entity.profiles;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import entity.profiles.PersonMemento;
import entity.datafiles.Phone;
import entity.datafiles.Name;
import entity.datafiles.Email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        name = new Name("George", "Michael", "she/her");
        this.personMemento = new PersonMemento(name, phone, email, "george2");
    }
    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Get name of PersonMemento")
    void testGetName(){
        String result = personMemento.getName();
        String expected = "George Michael";
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Get pronouns of PersonMemento")
    void testGetPronouns(){
        String result = personMemento.getPronouns();
        String expected = "she/her";
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Get original name of PersonMemento")
    void testGetOriginalName(){
        Name result = personMemento.getOriginalName();
        Name expected = this.name;
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Get phone of PersonMemento")
    void testGetPhone(){
        String result = personMemento.getPhone();
        String expected = "6475552401";
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Get email of PersonMemento")
    void testGetEmail(){
        String result = personMemento.getEmail();
        String expected = "jon666@gmail.com";
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Get username of PersonMemento")
    void testGetUsername(){
        String result = personMemento.getUsername();
        String expected = "george2";
        assertEquals(result, expected);
    }

}
