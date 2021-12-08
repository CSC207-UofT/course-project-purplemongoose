package entity.datafile;

import entity.datafiles.Email;
import entity.datafiles.Phone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the Email class
 *
 * @see Email
 */
class EmailTest {
    private Email email;

    @BeforeEach
    void setUp() {
        email = new Email("john.smith@email.com");
    }

    /**
     * Tests getting an email from an email object
     */
    @Test
    @DisplayName("Get an email")
    void getEmailTest() {
        String expected = "john.smith@email.com";
        String actual = email.getEmail();

        assertEquals(expected, actual);
    }

    /**
     * Tests for the equals override of Email
     */
    @Test
    @DisplayName("Test the equals override")
    void testCheckEqualObjects(){
        Email mail1 = new Email("cool@mail.com");
        Email mail2 = new Email("cool@mail.com");
        assertEquals(mail1, mail2);
    }
}