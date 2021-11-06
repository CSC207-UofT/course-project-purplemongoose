package entity.datafile;

import entity.datafiles.Name;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NameTest {

    Name name;
    Name name2;
    Name name3;

    @BeforeEach
    void setUp() {
        name = new Name("Heinz", "Doofenshmirtz", "he/him", "Dr.");
        name2 = new Name("Patrick", "Star", "he/him");
        name3 = new Name("SpongeBob", "Squarepants");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getFullName() {
        String fullName = name.getFullName();
        String fullName2 = name2.getFullName();
        String fullName3 = name3.getFullName();

        assertEquals(fullName, "Heinz Doofenshmirtz");
        assertEquals(fullName2, "Patrick Star");
        assertEquals(fullName3, "SpongeBob Squarepants");
    }

    @Test
    void getAllDetails() {
        String allDetails = name.getAllDetails();
        String allDetails2 = name2.getAllDetails();
        String allDetails3 = name3.getAllDetails();

        assertEquals(allDetails, "Dr. Heinz Doofenshmirtz (he/him)");
        assertEquals(allDetails2, "Patrick Star (he/him)");
        assertEquals(allDetails3, "SpongeBob Squarepants");
    }

    @Test
    void getTitles() {
        String title = name.getTitles();
        String title2 = name2.getTitles();
        String title3 = name3.getTitles();

        assertEquals(title, "Dr.");
        assertNull(title2);
        assertNull(title3);
    }

    @Test
    void getPronouns() {
        String pronouns = name.getPronouns();
        String pronouns2 = name2.getPronouns();
        String pronouns3 = name3.getPronouns();

        assertEquals(pronouns, "he/him");
        assertEquals(pronouns2, "he/him");
        assertNull(pronouns3);
    }
}
