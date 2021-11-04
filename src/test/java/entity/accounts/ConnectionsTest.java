package entity.accounts;

import entity.datafile.Email;
import entity.datafile.Name;
import entity.datafile.Phone;
import entity.profiles.Business;
import entity.profiles.Organization;
import entity.profiles.Person;
import entity.profiles.ProfileType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the Connections class
 *
 * @see Connections
 */
class ConnectionsTest {
    private HashMap<ProfileType, String> localStore;

    @BeforeEach
    void setUp() {
        localStore = new HashMap<>();
        localStore.put(new Business(
                "compName1",
                new Phone("5551234567"),
                new Email("name.name@comp.com"),
                "CompInc",
                new Name("Joe", "Smith")
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
        localStore.put(new Person(
                new Name("Smith", "Johnson"),
                new Phone("8881234567"),
                new Email("smith.joe@gmail.com"),
                "SmithJoe87"
        ), null);
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

        assertTrue(new Connections().addConnection(localStore, test_person));
        assertTrue(localStore.containsKey(test_person));
        assertNull(localStore.get(test_person));
    }

    /**
     * Tests adding an existing person to localStore through a Connections object
     */
    @Test
    @DisplayName("Try to add an existing connection")
    void addConnectionInMap() {
        Person test_person = new Person(
                new Name("Smith", "Johnson"),
                new Phone("8881234567"),
                new Email("smith.joe@gmail.com"),
                "SmithJoe87"
        );

        assertTrue(localStore.containsKey(test_person));
        assertFalse(new Connections().addConnection(localStore, test_person));
        assertTrue(localStore.containsKey(test_person));
    }

    /**
     * Tests removing a connection from localStore
     */
    @Test
    @DisplayName("Remove an existing connection")
    void removeConnectionInMap() {
        Person test_person = new Person(
                new Name("Smith", "Johnson"),
                new Phone("8881234567"),
                new Email("smith.joe@gmail.com"),
                "SmithJoe87"
        );

        assertTrue(localStore.containsKey(test_person));
        assertTrue(new Connections().removeConnection(localStore, test_person));
        assertFalse(localStore.containsKey(test_person));
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
        assertFalse(new Connections().removeConnection(localStore, test_person));
    }

    /**
     * Tests the getConnections method
     */
    @Test
    @DisplayName("Get the connections as a string")
    void getConnectionsTest() {
        String[] expected = """
orgName1 | name.name@org.com | 6661234567
SmithJohnson | smith.joe@gmail.com | 8881234567
compName1 | name.name@comp.com | 5551234567
JoeSmith | joe.smith@gmail.com | 7771234567
                """.split("\n");
        Arrays.sort(expected);

        String[] actual = new Connections().getConnections(localStore).split("\n");
        Arrays.sort(actual);

        assertIterableEquals(Arrays.asList(expected), Arrays.asList(actual));
    }

    /**
     * Tests the getConnections method on an empty Map
     */
    @Test
    @DisplayName("Get no connections as a string")
    void getConnectionsEmpty() {
        String expected = "";
        String actual = new Connections().getConnections(new HashMap<>());

        assertEquals(expected, actual);
    }
}