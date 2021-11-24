package database.SQLite.commands;

import database.SQLite.arguments.SQLiteSerializableArg;
import database.SQLite.arguments.SQLiteStringArg;
import entity.profiles.ProfileType;

/**
 * A statement for adding a profile to the MainFrame database.
 */
public class SQLiteAddProfileStatement extends SQLiteStatement{

    /**
     * Create a statement to add an account to the database.
     *
     * @param username the username of the account to add.
     * @param profile the profile object of the user to add.
     */
    public SQLiteAddProfileStatement(String username, ProfileType profile) {
        super("INSERT INTO profiles(username, profile) VALUES(?, ?)",
                new SQLiteStringArg(username),
                new SQLiteSerializableArg<ProfileType>(profile)
        );
    }
}
