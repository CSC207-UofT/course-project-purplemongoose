package database.gateway;

import database.SQLite.commands.SQLitePasswordQuery;
import database.SQLite.helpers.SQLiteDataBaseHelperMainFrame;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationGateway extends DatabaseGateway<SQLiteDataBaseHelperMainFrame>{
    /**
     * Creates an account gateway for the main frame located at path.
     *
     * @param path the path to the mainframe database.
     * @throws IOException if the path doesn't exist.
     */
    public AuthenticationGateway(String path) throws IOException {
        super(new SQLiteDataBaseHelperMainFrame(path));
    }

    /**
     * Creates an account gateway for a mainframe exising inn-memory.
     */
    public AuthenticationGateway() {
        super(new SQLiteDataBaseHelperMainFrame());
    }

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
