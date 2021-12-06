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

        Name name = new Name("John", "Smith", "he/him");
        Phone phone = new Phone("98239824");
        Email email = new Email("johnsmith@gmail.com");

        mementoManager.addPersonalMemento(name, phone, email, "john111");

        PersonMemento personMemento = mementoManager.getPersonalMemento(0);
        Name actual = personMemento.getOriginalName();
        assertTrue(name == actual);

    }

    @Test
    @DisplayName("Add and get a OrganizationMemento")
    void addAndGetOrganizationMemento() {
        String name = "Adidas";
        Phone phone = new Phone("938475002");
        Email email = new Email("adidas@gmail.com");

        mementoManager.addOrganizationMemento(name, phone, email, "adidas");

        OrganizationMemento organizationMemento = mementoManager.getOrganizationMemento(0);
        String actual = organizationMemento.getPhone();
        assertTrue("938475002" == actual);
    }

    @Test
    @DisplayName("Get memento history")
    void getHistory() {

        Name name = new Name("John", "Smith", "he/him");
        Phone phone = new Phone("98239824");
        Email email = new Email("johnsmith@gmail.com");

        String name2 = "Adidas";
        Phone phone2 = new Phone("938475002");
        Email email2 = new Email("adidas@gmail.com");

        mementoManager.addPersonalMemento(name, phone, email, "john111");
        mementoManager.addOrganizationMemento(name2, phone2, email2, "adidas");

        Memento[] hist = mementoManager.getHistory();
        assertTrue(hist[0].getEmail()=="adidas@gmail.com");
    }
}