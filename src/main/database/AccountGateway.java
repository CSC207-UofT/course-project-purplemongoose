package database;

import java.io.File;
import java.sql.*;

public class AccountGateway extends MainFrameGateway {

    private final String mfLocation = "data/account.db";

    @Override
    public Connection mfConnect() {
        File file = new File(mfLocation);
        Connection conn = null;

        if (file.exists()) {
            try {
                conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", this.mfLocation));
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        else { // if such a db doesn't exist, create one and add a table
            try {
                conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", this.mfLocation));
                createAccountTable(conn);
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return conn; // return a connection for other methods to use
    }

    // create table with username and password fields
    private void createAccountTable(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            String tableSQL = """
                        CREATE TABLE IF NOT EXISTS "accounts" (
                        	"username"	TEXT NOT NULL UNIQUE,
                        	"password"	TEXT NOT NULL,
                        	"uuid"      TEXT NOT NULL UNIQUE,
                        	"account"   BLOB
                        );""";
            stmt.execute(tableSQL);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Object getAccountData(String uuid) {
        String sqlQuery = "SELECT account FROM accounts WHERE uuid = ?";
        try {
            Connection conn = mfConnect();
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, uuid);
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
    public boolean insertAccountData(String username, String password, String uuid, Object acc) {
        String sqlQuery = "INSERT INTO accounts(username, password, uuid, account) VALUES(?, ?, ?, ?)";
        try {
            Connection conn = mfConnect();
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, uuid);
            stmt.setBytes(4, toBytes(acc));
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean updateAccountData(String uuid, Object acc) {
        String sqlQuery = "UPDATE accounts SET account = ? WHERE uuid = ?";
        try {
            Connection conn = mfConnect();
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setBytes(1, toBytes(acc));
            stmt.setString(2, uuid);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    // add delete account data

    public String authAccountData(String username, String password) {
        String getSQL = "SELECT uuid FROM accounts WHERE username = ? AND password = ?";
        try {
            Connection conn = mfConnect();
            PreparedStatement stmt = conn.prepareStatement(getSQL);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

}

