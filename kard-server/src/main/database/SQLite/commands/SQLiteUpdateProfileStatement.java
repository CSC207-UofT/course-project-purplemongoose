package database.SQLite.commands;

import database.SQLite.arguments.SQLiteSerializableArg;
import database.SQLite.arguments.SQLiteStringArg;
import entity.profiles.ProfileType;

/**
 * A statement for updating the profile of a user.
 */
public class SQLiteUpdateProfileStatement extends SQLiteStatement{

    /**
     * Creates a statement to update the profile data fo a user.
     *
     * @param username the username of the account.
     * @param profile  the new profile data to pass in to the statement.
     */
    public SQLiteUpdateProfileStatement(String username, ProfileType profile) {
        super("UPDATE profiles SET profile = ? WHERE username = ?",
                new SQLiteSerializableArg<ProfileType>(profile),
                new SQLiteStringArg(username)
        );
    }
}
