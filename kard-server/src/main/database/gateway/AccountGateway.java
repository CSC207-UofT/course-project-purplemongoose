package database.gateway;

import java.io.File;
import java.sql.*;

public class AccountGateway extends DatabaseGateway {

    @Override
    public Connection databaseConnect() {
        String mfLocation = "data/account.db";
        File file = new File(mfLocation);
        Connection conn = null;
        if (file.exists()) {
            try {
                conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", mfLocation));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        else { // if such a db doesn't exist, create one and add a table
            try {
                conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", mfLocation));
                createAccountTable(conn);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return conn; // return a connection for other methods to use
    }

    /**
     *
     * @param conn
     */
    public void createAccountTable(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            String tableSQL = """
                    CREATE TABLE IF NOT EXISTS "accounts" (
                    	"username"	TEXT NOT NULL UNIQUE,
                    	"password"	TEXT NOT NULL,
                    	"account"   BLOB
                    );""";
            stmt.execute(tableSQL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     *
     * @param username
     * @return
     */
    public Object getAccountData(String username) {
        String sqlQuery = "SELECT account FROM accounts WHERE username = ?";
        ResultSet rs = null;
        try (Connection conn = databaseConnect(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                byte[] objBytes = rs.getBytes(1);
                return toObject(objBytes);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {}
            }
        }
        return null;
    }

    /**
     *
     * @param username
     * @param password
     * @param acc
     * @return
     */
    public boolean insertAccountData(String username, String password, Object acc) {
        String sqlQuery = "INSERT INTO accounts(username, password, account) VALUES(?, ?, ?)";
        try (Connection conn = databaseConnect(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setBytes(3, toBytes(acc));
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     *
     * @param username
     * @param acc
     * @return
     */
    public boolean updateAccountData(String username, Object acc) {
        String sqlQuery = "UPDATE accounts SET account = ? WHERE username = ?";
        try (Connection conn = databaseConnect(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.setBytes(1, toBytes(acc));
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public String authAccountData(String username, String password) {
        String sqlQuery = "SELECT username FROM accounts WHERE username = ? AND password = ?";
        ResultSet rs = null;
        try (Connection conn = databaseConnect(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {}
            }
        }
        return null;
    }
    // add deleteAccountData
}
