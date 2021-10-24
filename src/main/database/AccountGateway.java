package database;

import java.sql.Connection;

public class AccountGateway extends MainFrameGateway {

    public AccountGateway() {
        super();
    }

    public static boolean generateUser(String username, String password) {
        // some sqlite code queries and code here
        return username.equals(password); //placeholder
    }

    // for testing purposes
    public static void main(String[] args) {
        AccountGateway gw = new AccountGateway();
        Connection conn = gw.mfConnect();
    }
}

