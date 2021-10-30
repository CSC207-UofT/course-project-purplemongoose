package database;

import entity.profiles.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class MainFrameTest {

    private final Person newUser = new Person("Name", "1234567890", "name@name.com");
    private final MainFrame mf = new MainFrame();

    @BeforeEach
    void setUp() {
        mf.addClient(newUser, newUser.getName());
    }

    @Test
    void query() {
        Client found = mf.query(newUser.getName());
        assertEquals( found, newUser);
    }

    @Test
    void removeEntity() {
        mf.removeClient(newUser.getName());

        Client found = mf.query(newUser.getName());
        assertNull(found);
    }
}