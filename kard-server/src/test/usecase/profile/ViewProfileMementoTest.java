package usecase.profile;

import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;
import entity.profiles.Person;
import entity.profiles.PersonMemento;
import entity.profiles.Memento;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import database.SQLite.SQLiteDataBaseManager;
import database.gateway.AccountGateway;
import database.gateway.ProfileGateway;
import usecase.account.CreateAccount;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ViewProfileMementoTest {
    private static CreateAccount createAccount;
    private static CreateProfile createProfile;
    private static ViewProfileMemento viewProfileMemento;
    private static UpdateProfile updateProfile;
    private static ViewProfile viewProfile;
    private static ProfileGateway pg;

    @BeforeAll
    static void setUp() {
        try {
            pg = new ProfileGateway("./ViewProfileMemento/mainframe.db");
            createAccount = new CreateAccount(
                    new AccountGateway("./ViewProfileMemento/mainframe.db"));
            createProfile = new CreateProfile(
                    pg);
            viewProfileMemento = new ViewProfileMemento(
                    pg
            );
            updateProfile = new UpdateProfile(
                    pg);
            viewProfile = new ViewProfile(
                    pg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDown() throws IOException {
        // Close the connection and delete the directory
        SQLiteDataBaseManager.close();
        FileUtils.forceDelete(new File("./ViewProfileMemento"));
    }

    /**
     * Tests viewing an account's profile memento after several edits
     */
    @Test
    @DisplayName("Create a profile, update it, then ask to view its memento history")
    void ViewProfileMementoSuccess() throws InterruptedException {
        createAccount.newPersonalAccount("joe", "123");
        TimeUnit.SECONDS.sleep(1);
        createProfile.newPerson("joe", "John", "Smith", "He/Him", "Dr.", "6471234567", "joe.mama@joe.io");
        TimeUnit.SECONDS.sleep(1);
        updateProfile.updatePersonProfile("joe", "Johnny", "Cash", "He/Him", "Mr.", "6471223234", "johnny@cash.com");
        TimeUnit.SECONDS.sleep(1);
        updateProfile.updatePersonProfile("joe", "Bob", "Bruh", "He/They", "Mr.", "123456", "bruh@bruh.com");
        TimeUnit.SECONDS.sleep(1);

        PersonMemento p0 = new PersonMemento(
                new Name("John", "Smith", "He/Him", "Dr."),
                new Phone("6471234567"),
                new Email("joe.mama@joe.io"),
                "joe");
        PersonMemento p1 = new PersonMemento(
                new Name("Johnny", "Cash", "He/Him", "Mr."),
                new Phone("6471223234"),
                new Email("johnny@cash.com"),
                "joe");
        PersonMemento p2 = new PersonMemento(
                new Name("Bob", "Bruh", "He/They", "Mr."),
                new Phone("123456"),
                new Email("bruh@bruh.com"),
                "joe");
        Memento[] actual = viewProfileMemento.viewProfileMemento("joe");
        assertEquals(p0.getName(), actual[0].getName());
        assertEquals(p1.getName(), actual[1].getName());
        assertEquals(p2.getName(), actual[2].getName());
    }

}