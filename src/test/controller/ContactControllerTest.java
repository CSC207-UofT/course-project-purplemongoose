package controller;

import database.MainFrame;
import entity.Person;
import entity.PersonalUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ContactControllerTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private final MainFrame mf = new MainFrame();
    private final PersonalUser user = new PersonalUser();

    private final Person newUser = new Person("Name", "1234567890", "name@name.com");
    private final Person whoops = new Person ("Whoops", "7777777777", "whoops@whoops.com");
    private final ContactController cc = new ContactController(mf, user);

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        mf.addClient(newUser, newUser.getName());
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void addContactMainFrameNotExisting() {
        cc.addContactMainFrame(newUser.getName());
        String expected = "Name has been successfully added!";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void addContactMainFrameExisting() {
        System.setOut(standardOut);
        cc.addContactMainFrame(newUser.getName());
        System.setOut(new PrintStream(outputStreamCaptor));
        cc.addContactMainFrame(newUser.getName());
        String expected = "Name is already a contact!";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void addContactMainFrameNotFound() {
        cc.addContactMainFrame(whoops.getName());
        String expected = "Whoops could not be found!";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void removeContactMainFrameNotExisting() {
        cc.removeContactMainFrame(newUser.getName());
        String expected = "Name is not a contact!";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void removeContactMainFrameExisting() {
        System.setOut(standardOut);
        cc.addContactMainFrame(newUser.getName());
        System.setOut(new PrintStream(outputStreamCaptor));
        cc.removeContactMainFrame(newUser.getName());
        String expected = "Name has been successfully removed!";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void removeContactMainFrameNotFound() {
        cc.removeContactMainFrame(whoops.getName());
        String expected = "Whoops could not be found!";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }
}