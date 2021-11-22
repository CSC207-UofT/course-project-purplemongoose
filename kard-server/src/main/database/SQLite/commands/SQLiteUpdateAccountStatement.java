package database.SQLite.commands;

import database.SQLite.arguments.SQLiteSerializableArg;
import database.SQLite.arguments.SQLiteStringArg;
import entity.accounts.Account;

public class SQLiteUpdateAccountStatement extends SQLiteStatement{

    /**
     * Creates a statement to update the account data fo a user
     *
     * @param username the username of the account.
     * @param account  the new account data to pass in to the statement
     */
    public SQLiteUpdateAccountStatement(String username, Account account) {
        super("UPDATE accounts SET account = ? WHERE username = ?",
                new SQLiteSerializableArg<Account>(account),
                new SQLiteStringArg(username)
                );
    }
}
