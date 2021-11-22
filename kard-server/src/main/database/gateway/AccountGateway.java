package database.gateway;

import database.SQLite.SQLiteDataBase;
import database.SQLite.commands.SQLiteAccountDataQuery;
import database.SQLite.commands.SQLiteAddAccountStatement;
import database.SQLite.helpers.SQLiteDataBaseHelperMainFrame;
import entity.accounts.Account;

import java.sql.*;

public class AccountGateway extends DatabaseGateway<SQLiteDataBaseHelperMainFrame> {

    /**
     * Fetch an account object from the database given the username of the account.
     *
     * @param username the username of the user.
     * @return the account of the user.
     */
    public Account getAccountData(String username) {
        SQLiteAccountDataQuery query = new SQLiteAccountDataQuery(username);
        this.dbHelper.executeStatement(query);

        return query.getAccount();
    }

    /**
     * Add an account to the database.
     *
     * @param username the username of the account to add.
     * @param password the password of the account to add.
     * @param account the account object of the user to add.
     * @return whether the account could be added to the database.
     */
    public boolean insertAccountData(String username, String password, Account account) {
        return this.dbHelper.executeStatement(new SQLiteAddAccountStatement(
                username,
                password,
                account
        ));
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
