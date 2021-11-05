package entity.datafile;

import entity.datafiles.Phone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {
    private Phone phone;

    @BeforeEach
    void setUp() {
        phone = new Phone("5554443333");
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Tests getting a phone number
     */
    @Test
    @DisplayName("Get a phone number")
    void getPhoneTest() {
        String expected = "5554443333";
        String actual = phone.getPhone();

        assertEquals(actual, expected);
    }
}