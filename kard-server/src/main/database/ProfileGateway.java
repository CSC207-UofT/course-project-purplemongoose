package database;

import java.io.File;
import java.sql.*;

public class ProfileGateway extends DatabaseGateway {

    @Override
    public Connection databaseConnect() {
        String mfLocation = "data/profile.db";
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
                createProfileTable(conn);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return conn; // return a connection for other methods to use
    }

    public void createProfileTable(Connection conn) {
        try (Statement stmt = conn.createStatement()){
            String tableSQL = """
                        CREATE TABLE IF NOT EXISTS "profiles" (
                        	"username"  TEXT NOT NULL UNIQUE,
                        	"profile"   BLOB
                        );""";
            stmt.execute(tableSQL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Object getProfileData(String username) {
        String sqlQuery = "SELECT profile FROM profiles WHERE username = ?";
        ResultSet rs = null;
        try (Connection conn = databaseConnect(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
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

    public boolean insertProfileData(String username, Object prof) {
        String sqlQuery = "INSERT INTO profiles(username, profile) VALUES(?, ?)";
        try (Connection conn = databaseConnect(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
            ps.setString(1, username);
            ps.setBytes(2, toBytes(prof));
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean updateProfileData(String username, Object prof) {
        String sqlQuery = "UPDATE profiles SET profile = ? WHERE username = ?";
        try (Connection conn = databaseConnect(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
            ps.setBytes(1, toBytes(prof));
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
    // add delete profile data
}
