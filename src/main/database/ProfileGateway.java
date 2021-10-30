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
        try {
            Statement stmt = conn.createStatement();
            String tableSQL = """
                        CREATE TABLE IF NOT EXISTS "profiles" (
                        	"uuid"	    TEXT NOT NULL UNIQUE,
                        	"profile"   BLOB
                        );""";
            stmt.execute(tableSQL);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Object getProfileData(String uuid) {
        String getSQL = "SELECT profile FROM profiles WHERE uuid = ?";
        try {
            Connection conn = mfConnect();
            PreparedStatement stmt = conn.prepareStatement(getSQL);
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

    public boolean insertProfileData(String uuid, Object prof) {
        String insertSQL = "INSERT INTO profiles(uuid, profile) VALUES(?, ?)";
        try {
            Connection conn = mfConnect();
            PreparedStatement stmt = conn.prepareStatement(insertSQL);
            stmt.setString(1, uuid);
            stmt.setBytes(2, toBytes(prof));
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean updateProfileData(String uuid, Object prof) {
        String updateSQL = "UPDATE profiles SET profile = ? WHERE uuid = ?";
        try {
            Connection conn = mfConnect();
            PreparedStatement stmt = conn.prepareStatement(updateSQL);
            stmt.setBytes(1, toBytes(prof));
            stmt.setString(2, uuid);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    // add delete profile data
}
