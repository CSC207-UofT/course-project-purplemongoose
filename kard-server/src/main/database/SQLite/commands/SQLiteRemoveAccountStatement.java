package database.SQLite.commands;

import database.SQLite.arguments.SQLiteStringArg;

/**
 * A statement to remove an account from the database.
 */
public class SQLiteRemoveAccountStatement extends SQLiteStatement{
    /**
     * Creates a statement to remove a user from the database.
     *
     * @param username name of the user.
     */
    public SQLiteRemoveAccountStatement(String username) {
        super("DELETE FROM accounts WHERE username = ?",
                new SQLiteStringArg(username)
        );
    }
}
