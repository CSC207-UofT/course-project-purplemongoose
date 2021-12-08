package usecase.account;
import database.gateway.AccountGateway;
import database.gateway.ProfileGateway;
import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;
import entity.profiles.PersonMemento;
import entity.profiles.ProfileType;
import entity.profiles.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import usecase.account.SortByName;
import usecase.profile.CreateProfile;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class SortByNameTest {

    /**
     * Tests sorting an array of profiles in ascending order of name
     */
    @Test
    @DisplayName("sort an array of profiles in ascending order of name")
    void TestSortAscend(){
        SortByName s = new SortByName();
        ProfileType p0 = new Person(
                new Name("Apple", "Smith", "He/Him", "Dr."),
                new Phone("6471234567"),
                new Email("joe.mama@joe.io"),
                "p0");
        ProfileType p1 = new Person(
                new Name("Bob", "Cash", "He/Him", "Mr."),
                new Phone("6471223234"),
                new Email("johnny@cash.com"),
                "p1");
        ProfileType p2 = new Person(
                new Name("Chad", "Bruh", "He/They", "Mr."),
                new Phone("123456"),
                new Email("bruh@bruh.com"),
                "p2");

        ProfileType[] temp = {p2, p0, p1};
        s.sort(temp, "ascend");
        assertEquals(temp[0].getName(), "Dr. Apple Smith");
        assertEquals(temp[1].getName(), "Mr. Bob Cash");
        assertEquals(temp[2].getName(), "Mr. Chad Bruh");
    }

    /**
     * Tests sorting an array of profiles in descending order of name
     */
    @Test
    @DisplayName("sort an array of profiles in descending order of name")
    void TestSortDescend(){
        SortByName s = new SortByName();
        ProfileType p0 = new Person(
                new Name("Apple", "Smith", "He/Him", "Dr."),
                new Phone("6471234567"),
                new Email("joe.mama@joe.io"),
                "p0");
        ProfileType p1 = new Person(
                new Name("Bob", "Cash", "He/Him", "Mr."),
                new Phone("6471223234"),
                new Email("johnny@cash.com"),
                "p1");
        ProfileType p2 = new Person(
                new Name("Chad", "Bruh", "He/They", "Mr."),
                new Phone("123456"),
                new Email("bruh@bruh.com"),
                "p2");

        ProfileType[] temp = {p1, p0, p2};
        s.sort(temp, "descend");
        assertEquals(temp[2].getName(), "Dr. Apple Smith");
        assertEquals(temp[1].getName(), "Mr. Bob Cash");
        assertEquals(temp[0].getName(), "Mr. Chad Bruh");
    }
}
