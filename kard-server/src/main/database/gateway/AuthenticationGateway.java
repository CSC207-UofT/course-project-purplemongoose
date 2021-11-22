package database.gateway;

import database.SQLite.commands.SQLitePasswordQuery;
import database.SQLite.helpers.SQLiteDataBaseHelperMainFrame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationGateway extends DatabaseGateway<SQLiteDataBaseHelperMainFrame>{
    /**
     *  Authenticate account data
     *
     * @param username
     * @param password
     * @return whether password matches the stored password for the username.
     */
    public boolean authAccountData(String username, String password) {
        SQLitePasswordQuery passwordQuery = new SQLitePasswordQuery(username, password);
        this.dbHelper.executeStatement(passwordQuery);

        return passwordQuery.matches();
    }
}
