package database;

import java.io.File;
import java.sql.*;

public class AuthGateway extends MainFrameGateway{

    private final String mfLocation = "data/account.db";

    @Override
    Connection mfConnect() {
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
        return conn;
    }

    public boolean authLoginInfo(String username, String password) {
        String getSQL = "SELECT data FROM accounts WHERE username = ? AND password = ?";
        try {
            Connection conn = mfConnect();
            PreparedStatement stmt = conn.prepareStatement(getSQL);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}
