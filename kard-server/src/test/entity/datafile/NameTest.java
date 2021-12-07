package entity.datafile;

import entity.datafiles.Name;
import entity.datafiles.Phone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * A class for testing the Name class
 *
 * @see Name
 */

class NameTest {

    Name name;
    Name name2;
    Name name3;

    @BeforeEach
    void setUp() {
        name = new Name("Heinz", "Doofenshmirtz", "he/him", "Dr.");
        name2 = new Name("Patrick", "Star", "he/him", "");
        name3 = new Name("SpongeBob", "Squarepants", "", "");
    }

    /**
     * Tests getting the full name
     */

    @Test
    @DisplayName("Get full name")
    void getFullName() {
        String fullName = name.getFullName();
        String fullName2 = name2.getFullName();
        String fullName3 = name3.getFullName();

        assertEquals(fullName, "Dr. Heinz Doofenshmirtz");
        assertEquals(fullName2, " Patrick Star");
        assertEquals(fullName3, " SpongeBob Squarepants");
    }

    /**
     * Tests getting pronouns
     */

    @Test
    @DisplayName("Get pronouns")
    void getPronouns() {
        String pronouns = name.getPronouns();
        String pronouns2 = name2.getPronouns();
        String pronouns3 = name3.getPronouns();

        assertEquals(pronouns, "he/him");
        assertEquals(pronouns2, "he/him");
        assertEquals(pronouns3, "");
    }

    /**
     * Tests for the equals override of Name
     */
    @Test
    @DisplayName("Test the equals override")
    void testCheckEqualObjects(){
        Name name1 = new Name("Heinz", "Doofenshmirtz", "he/him", "Dr.");
        Name name2 = new Name("Heinz", "Doofenshmirtz", "he/him", "Dr.");
        assertEquals(name1, name2);
    }
}
