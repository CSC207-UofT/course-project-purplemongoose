package database.SQLite.commands;

import database.SQLite.arguments.SQLiteSerializableArg;
import database.SQLite.arguments.SQLiteStringArg;
import entity.accounts.Account;
import util.crypto.SHA256Hash;

/**
 * A statement for adding an account to the MainFrame database.
 */
public class SQLiteAddAccountStatement extends SQLiteStatement{

    /**
     * Create a statement to add an account to the database.
     *
     * @param username the username of the account to add.
     * @param password the password of the account to add.
     * @param account the account object of the user to add.
     */
    public SQLiteAddAccountStatement(String username, String password, Account account) {
        super("INSERT INTO accounts(username, password, account) VALUES(?, ?, ?)",
                new SQLiteStringArg(username),
                new SQLiteStringArg(new SHA256Hash().hash(password, username)),
                new SQLiteSerializableArg<Account>(account)
        );
    }
}
