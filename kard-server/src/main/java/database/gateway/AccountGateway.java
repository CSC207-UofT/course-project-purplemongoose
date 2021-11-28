package database.gateway;

import database.SQLite.SQLiteDataBase;
import database.SQLite.commands.SQLiteAccountDataQuery;
import database.SQLite.commands.SQLiteAddAccountStatement;
import database.SQLite.commands.SQLiteRemoveAccountStatement;
import database.SQLite.commands.SQLiteUpdateAccountStatement;
import database.SQLite.helpers.SQLiteDataBaseHelperMainFrame;
import entity.accounts.Account;

import java.io.IOException;
import java.sql.*;

public class AccountGateway extends DatabaseGateway<SQLiteDataBaseHelperMainFrame> {
    /**
     * Creates an account gateway for the main frame located at path.
     *
     * @param path the path to the mainframe database.
     * @throws IOException if the path doesn't exist.
     */
    public AccountGateway(String path) throws IOException {
        super(new SQLiteDataBaseHelperMainFrame(path));
    }

    /**
     * Creates an account gateway for a mainframe exising inn-memory.
     */
    public AccountGateway() {
        super(new SQLiteDataBaseHelperMainFrame());
    }

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
     * Updates a user's account data in the database.
     *
     * @param username the username of the user.
     * @param account the account object to update to.
     * @return whether the account could be updated.
     */
    public boolean updateAccountData(String username, Account account) {
        return this.dbHelper.executeStatement(new SQLiteUpdateAccountStatement(
                username,
                account
        ));
    }

    /**
     * Removes a users account data from the database.
     *
     * @param username the name of the user to remove.
     * @return whether the user could successfully be removed from the database.
     */
    public boolean deleteAccountData(String username) {
        return this.dbHelper.executeStatement(new SQLiteRemoveAccountStatement(
                username
        ));
    }
}
