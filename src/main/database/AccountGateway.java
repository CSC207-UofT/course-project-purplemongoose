package database;

import entity.Client;
import entity.profiles.Person;

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
        Person p1 = new Person("bob", "111-222-3333", "hello@bob.com");
        Person p2 = new Person("joe", "647-647-6477", "joe@mama.com");
        Person p3 = new Person("sandy", "123-456-7890", "sandy@cheeks.com");

        if (true) { // set as false if entries are already in db
            insertUserData("bobthegamer", "password", p1);
            insertUserData("joemama69", "hahagotem", p2);
            insertUserData("squirrel123", "nuts", p3);
        }
        else {
            Client pd1 = (Client)getUserData("bobthegamer", "password");
            Client pd2 = (Client)getUserData("joemama69", "hahagotem");
            Client pd3 = (Client)getUserData("squirrel123", "nuts");

            assert pd1 != null;
            assert pd2 != null;
            assert pd3 != null;

            System.out.printf("%s | %s | %s\n", pd1.getName(), pd1.getPhone(), pd1.getEmail());
            System.out.printf("%s | %s | %s\n", pd2.getName(), pd2.getPhone(), pd2.getEmail());
            System.out.printf("%s | %s | %s\n", pd3.getName(), pd3.getPhone(), pd3.getEmail());

        }
    }
}

