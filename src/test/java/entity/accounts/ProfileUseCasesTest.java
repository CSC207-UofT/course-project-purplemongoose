package entity.accounts;

import database.ProfileGateway;
import entity.datafiles.Email;
import entity.datafiles.Phone;
import entity.profiles.Business;
import entity.profiles.Organization;
import entity.profiles.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import usecase.ProfileUseCases;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the ProfileUseCases class
 *
 * @see ProfileUseCases
 */

public class ProfileUseCasesTest {
    Person profile;
    Business biz;
    Organization org;
    ProfileUseCases puc;
    ProfileGateway pg = new ProfileGateway();

    @BeforeEach
    void setUp() {

        Phone phone = new Phone("8347586347");
        Email email = new Email("adidas@mail.com");
        org = new Organization("Adidas", phone, email, "adidas");
        this.pg.insertProfileData("adidas", org);
    }
}
