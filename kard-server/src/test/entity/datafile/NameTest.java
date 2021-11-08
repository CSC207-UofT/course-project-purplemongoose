package entity.datafile;

import entity.datafiles.Name;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    }

    @Test
    void getTitles() {
    }

    @Test
    void getPronouns() {
    }
}
