package entity.accounts;

import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;
import entity.profiles.Business;
import entity.profiles.Organization;
import entity.profiles.Person;
import entity.profiles.ProfileType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the Connections class
 *
 * @see Connections
 */
class ConnectionsTest {
    private HashMap<ProfileType, String> localStore;
    private Person test_person_1;

    @BeforeEach
    void setUp() {
        localStore = new HashMap<>();
        test_person_1 = new Person(
                new Name("Smith", "Johnson"),
                new Phone("8881234567"),
                new Email("smith.joe@gmail.com"),
                "SmithJoe87"
        );

        localStore.put(new Business(
                "compName1",
                new Phone("5551234567"),
                new Email("name.name@comp.com"),
                "CompInc",
                new Person(new Name("bob", "smith", null, null), new Phone("123-456-7777"),
                        new Email("bob@bobmail.com"), "the_bobinator")
        ), null);
        localStore.put(new Organization(
                "orgName1",
                new Phone("6661234567"),
                new Email("name.name@org.com"),
                "OrgInc"
        ), null);
        localStore.put(new Person(
                new Name("Joe", "Smith"),
                new Phone("7771234567"),
                new Email("joe.smith@gmail.com"),
                "JoeSmith78"
        ), null);
        localStore.put(test_person_1, null);

    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Tests adding a person to localStore through a Connections object
     */
    @Test
    @DisplayName("Add a new connection.")
    void addConnectionNotInMap() {
        Person test_person = new Person(
                new Name("Jenny", "Smith"),
                new Phone("9991234567"),
                new Email("smith.jen@gmail.com"),
                "JenSmith87"
        );

        assertTrue(Connections.addConnection(localStore, test_person));
        assertTrue(localStore.containsKey(test_person));
        assertNull(localStore.get(test_person));
    }

    /**
     * Tests adding an existing person to localStore through a Connections object
     */
    @Test
    @DisplayName("Try to add an existing connection")
    void addConnectionInMap() {
        assertTrue(localStore.containsKey(test_person_1));
        assertFalse(Connections.addConnection(localStore, test_person_1));
        assertTrue(localStore.containsKey(test_person_1));
    }

    /**
     * Tests removing a connection from localStore
     */
    @Test
    @DisplayName("Remove an existing connection")
    void removeConnectionInMap() {
        assertTrue(localStore.containsKey(test_person_1));
        assertFalse(Connections.removeConnection(localStore, test_person_1));
        assertFalse(localStore.containsKey(test_person_1));
    }

    /**
     * Tests removing a connection from localStore that doesn't exist
     */
    @Test
    @DisplayName("Remove an non-existing connection")
    void removeConnectionNotInMap() {
        Person test_person = new Person(
                new Name("Jenny", "Smith"),
                new Phone("9991234567"),
                new Email("smith.jen@gmail.com"),
                "JenSmith87"
        );

        assertFalse(localStore.containsKey(test_person));
        assertFalse(Connections.removeConnection(localStore, test_person));
    }

}