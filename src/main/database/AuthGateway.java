package database;

import java.sql.*;

public final class AuthGateway extends MainFrameGateway {

    public AuthGateway() {
        super();
    }

    // return value subject to change
    public static boolean checkLoginInfo(String username, String password) {
        // some sqlite code queries and code here
        return username.equals(password); //placeholder
    }

    // for testing purposes
    public static void main(String[] args) {
        AuthGateway gw = new AuthGateway();
        Connection conn = gw.mfConnect();
    }
}
