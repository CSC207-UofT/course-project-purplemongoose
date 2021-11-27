package database.SQLite.commands;

import database.SQLite.arguments.SQLiteStringArg;
import entity.profiles.MementoManager;
import util.BytesSerializerDeserializer;

import java.io.Serial;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An SQL query for fetching the memento data of a user's profiles.
 */
public class SQLiteMementoQuery extends SQLiteQuery{

    /**
     * Create the query for the profile memento for the given name.
     *
     * @param username the username of the user to query from.
     */
    public SQLiteMementoQuery(String username) {
        super("SELECT memento FROM profiles WHERE username = ?",
                new SQLiteStringArg(username)
        );
    }

    /**
     * Get the profile memento corresponding to the username.
     *
     * @return the profile of the user, null if there is no such entry in the database or the results could not be
     * iterated through.
     */
    public MementoManager getMemento() {
        ResultSet results = this.getResults();

        try {
            if (results.next()) {
                byte[] mementoAsBytes = results.getBytes(1);
                return new BytesSerializerDeserializer<MementoManager>(mementoAsBytes).getAsSerializable();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
