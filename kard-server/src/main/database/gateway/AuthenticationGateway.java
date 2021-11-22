package database.gateway;

import database.SQLite.helpers.SQLiteDataBaseHelperMainFrame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationGateway extends DatabaseGateway<SQLiteDataBaseHelperMainFrame>{
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
}
