package entity.profiles;

import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MementoManagerTest {

    MementoManager mementoManager;

    @BeforeEach
    void setUp() {
        this.mementoManager = new MementoManager();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Add and get a PersonMemento")
    void addAndGetPersonalMemento() {

        Name name = new Name("John", "Smith", "he/him", "Mr.");
        Phone phone = new Phone("98239824");
        Email email = new Email("johnsmith@gmail.com");

        mementoManager.addPersonalMemento(name, phone, email, "john111");

        PersonMemento personMemento = mementoManager.getPersonalMemento(0);
        Name actual = personMemento.getOriginalName();
        assertSame(name, actual);

    }


    @Test
    @DisplayName("Get Memento history")
    void getHistory() {

        Name name = new Name("John", "Smith", "he/him", "Mr.");
        Phone phone = new Phone("98239824");
        Email email = new Email("johnsmith@gmail.com");

        Name name2 = new Name("John", "Smith", "he/him", "Dr..");
        Phone phone2 = new Phone("938475002");
        Email email2 = new Email("adidas@gmail.com");

        mementoManager.addPersonalMemento(name, phone, email, "john111");
        mementoManager.addPersonalMemento(name2, phone2, email2, "john111");

        Memento[] hist = mementoManager.getHistory();
        assertSame("adidas@gmail.com", hist[0].getEmail());
    }
}