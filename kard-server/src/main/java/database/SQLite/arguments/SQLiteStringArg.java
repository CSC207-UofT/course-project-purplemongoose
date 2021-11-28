package database.SQLite.arguments;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A class for a string argument to be passed to the prepared statement.
 */
public class SQLiteStringArg extends SQLiteArg<String> {

    /**
     * Create a string argument object with the  argument arg.
     * @param arg the argument of the string argument.
     */
    public SQLiteStringArg(String arg) {
        super(arg);
    }

    @Override
    public void setArg(int index, PreparedStatement statement) throws SQLException {
        statement.setString(index, arg);
    }
}
