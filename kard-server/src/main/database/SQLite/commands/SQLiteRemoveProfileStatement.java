package database.SQLite.commands;

import database.SQLite.arguments.SQLiteStringArg;

/**
 * A statement to remove an account from the database.
 */
public class SQLiteRemoveProfileStatement extends SQLiteStatement {
    public SQLiteRemoveProfileStatement(String username) {
        super("DELETE FROM profiles WHERE username = ?",
                new SQLiteStringArg(username)
        );
    }
}
