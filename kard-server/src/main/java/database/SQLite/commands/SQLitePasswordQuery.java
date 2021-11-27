package database.SQLite.commands;

import database.SQLite.arguments.SQLiteStringArg;
import util.crypto.SHA256Hash;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLitePasswordQuery extends SQLiteQuery{
    private String username;
    private String password;

    /**
     * Creates an SQLQuery, with arguments args.
     *
     * @param username the username of the user.
     * @param password the password to authenticate.
     */
    public SQLitePasswordQuery(String username, String password) {
        super("SELECT password FROM accounts WHERE username = ?",
                new SQLiteStringArg(username)
        );

        this.username = username;
        this.password = password;
    }

    /**
     * Return whether the hashed password matches the stored password using SHA256.
     *
     * @return whether the hashed password matches the stored password using SHA256.
     */
    public boolean matches() {
        ResultSet results = getResults();

        try {
            if (results.next()) {
                String hashed = new SHA256Hash().hash(password, username);
                String real = results.getString(1);

                return real.equals(hashed);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
