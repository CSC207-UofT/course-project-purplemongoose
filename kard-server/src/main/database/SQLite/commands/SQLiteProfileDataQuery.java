package database.SQLite.commands;

import database.SQLite.arguments.SQLiteStringArg;
import entity.profiles.ProfileType;
import util.BytesSerializerDeserializer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An SQL query for fetching the profile data of a user.
 */
public class SQLiteProfileDataQuery extends SQLiteQuery{

    /**
     * Create the query for the profile data for the given name.
     *
     * @param username the username of the user to query from.
     */
    public SQLiteProfileDataQuery(String username) {
        super("SELECT profile FROM profiles WHERE username = ?",
                new SQLiteStringArg(username)
        );
    }

    /**
     * Get the profile corresponding to the username.
     *
     * @return the profile of the user, null if there is no such entry in the database or the results could not be
     * iterated through.
     */
    public ProfileType getProfile() {
        ResultSet results = this.getResults();

        try {
            if (results.next()) {
                byte[] profileAsBytes = results.getBytes(1);
                return new BytesSerializerDeserializer<ProfileType>(profileAsBytes).getAsSerializable();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

}
