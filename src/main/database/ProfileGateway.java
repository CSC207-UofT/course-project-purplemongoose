package database;

import java.io.File;
import java.sql.*;

public class ProfileGateway extends MainFrameGateway {

    private final String mfLocation = "data/profile.db";

    @Override
    public Connection mfConnect() {
        File file = new File(mfLocation);
        Connection conn = null;
        if (file.exists()) {
            try {
                conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", this.mfLocation));
                return conn; // return a connection for other methods to use
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        else { // if such a db doesn't exist, create one and add a table
            try {
                conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", this.mfLocation));
                createProfileTable(conn);
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return conn; // return a connection for other methods to use
    }

    public void createProfileTable(Connection conn) {
        try (conn; Statement stmt = conn.createStatement()){
            String tableSQL = """
                        CREATE TABLE IF NOT EXISTS "profiles" (
                        	"uuid"	    TEXT NOT NULL UNIQUE,
                        	"profile"   BLOB
                        );""";
            stmt.execute(tableSQL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Object getProfileData(String uuid) {
        String sqlQuery = "SELECT profile FROM profiles WHERE uuid = ?";
        ResultSet rs = null;
        try (Connection conn = mfConnect(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.setString(1, uuid);
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

    public boolean insertProfileData(String uuid, Object prof) {
        String sqlQuery = "INSERT INTO profiles(uuid, profile) VALUES(?, ?)";
        try (Connection conn = mfConnect(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
            ps.setString(1, uuid);
            ps.setBytes(2, toBytes(prof));
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean updateProfileData(String uuid, Object prof) {
        String sqlQuery = "UPDATE profiles SET profile = ? WHERE uuid = ?";
        try (Connection conn = mfConnect(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
            ps.setBytes(1, toBytes(prof));
            ps.setString(2, uuid);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
    // add delete profile data
}
