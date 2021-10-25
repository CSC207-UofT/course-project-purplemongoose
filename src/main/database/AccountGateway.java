package database;

import entity.Person;

import java.sql.*;

public class AccountGateway extends MainFrameGateway {

    public AccountGateway() {
        super();
    }

    public static boolean generateUser(String username, String password) {
        // some sqlite code queries and code here
        return username.equals(password); //placeholder
    }

    public static Object getUserData(String username, String password) {
        String sqlQuery = "SELECT data FROM users WHERE username = ? AND password = ?";
        try {
            Connection conn = mfConnect();
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                byte[] objBytes = rs.getBytes(1);
                return toObject(objBytes);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    // should only be used when the user creates an account
    public static void insertUserData(String username, String password, Object user) {
        String sqlQuery = "INSERT INTO users(username, password, data) VALUES(?, ?, ?)";
        try {
            Connection conn = mfConnect();
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setBytes(3, toBytes(user));
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public static void updateUserData(String username, String password, Object user) {
        // WIP
    }


    // for testing purposes
    public static void main(String[] args) {
        Person p1 = new Person("bob", "1234567890", "hello@bob.com");
        insertUserData("bob", "pass", p1); // comment out if already inserted
        Person p2 = (Person)getUserData("bob", "pass");
        assert p2 != null;
        System.out.println(p2.getEmail());
    }
}

