package database.SQLite.commands;

import database.SQLite.arguments.SQLiteStringArg;
import entity.accounts.Account;
import util.BytesSerializerDeserializer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An SQL query for fetching the account data of a user.
 */
public class SQLiteAccountDataQuery extends SQLiteQuery{

    /**
     * Create the query for the account data for the given name.
     *
     * @param username the username of the user to query from.
     */
    public SQLiteAccountDataQuery(String username) {
        super("SELECT account FROM accounts WHERE username = ?", new SQLiteStringArg(username));
    }

    /**
     * Get the account corresponding to the username.
     *
     * @return the account of the user, null if there is no such entry in the database or the results could not be
     * iterated through.
     */
    public Account getAccount() {
        ResultSet results = this.getResults();

        try {
            if (results.next()) {
                byte[] accountAsBytes = results.getBytes(1);
                return new BytesSerializerDeserializer<Account>(accountAsBytes).getAsSerializable();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }
}
