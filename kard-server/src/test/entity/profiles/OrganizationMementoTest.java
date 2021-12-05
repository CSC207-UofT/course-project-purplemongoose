package entity.profiles;


import entity.datafiles.Email;
import entity.datafiles.Phone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class for testing the PersonMemento class
 *
 * @see OrganizationMemento
 */

public class OrganizationMementoTest {

    OrganizationMemento organizationMemento;
    Phone phone;
    Email email;
    String name;
    String username;

    @BeforeEach
    void setUp() {

        phone = new Phone("983475938");
        email = new Email("pizzahut@gmail.com");
        name = "Pizza Hut";
        this.organizationMemento = new OrganizationMemento(name, phone, email, "pizzahut");
    }
    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Get name of OrganizationMemento")
    void testGetName(){
        String result = organizationMemento.getName();
        String expected = "Pizza Hut";
        assertEquals(result, expected);
    }


    @Test
    @DisplayName("Get phone of OrganizationMemento")
    void testGetPhone(){
        String result = organizationMemento.getPhone();
        String expected = "983475938";
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Get email of OrganizationMemento")
    void testGetEmail(){
        String result = organizationMemento.getEmail();
        String expected = "pizzahut@gmail.com";
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Get username of OrganizationMemento")
    void testGetUsername(){
        String result = organizationMemento.getUsername();
        String expected = "pizzahut";
        assertEquals(result, expected);
    }

}
