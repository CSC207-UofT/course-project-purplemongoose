package util.crypto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SHA256HashTest {

    @Test
    @DisplayName("Test same values generate same result")
    public void testSame() {
        String username1 = new String("test1");
        String username2 = new String("test1");
        String password1 = new String("password");
        String password2 = new String("password");

        String hashed1 =  new SHA256Hash().hash(password1, username1);
        String hashed2 =  new SHA256Hash().hash(password2, username2);

        assertEquals(hashed1, hashed2);
    }

}