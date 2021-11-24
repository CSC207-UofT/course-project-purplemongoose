package database.SQLite.arguments;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A class for representing an SQL argument to be passed to a prepared statement.
 */
public abstract class SQLiteArg<T> {
    protected T arg;

    /**
     * Create an instance of the SQLite argument class with arg as the argument.
     *
     * @param arg the argument that the SQLiteArg instance is for.
     */
    public SQLiteArg(T arg) {
        this.arg = arg;
    }

    /**
     * Sets the argument at index of the prepared statement.
     *
     * @param index the index of the argument to set (starts at 1).
     * @param statement the statement to set that argument in.
     * @throws SQLException if the argument could not be set.
     */
    public void setArg(int index, PreparedStatement statement) throws SQLException {
        statement.setObject(index, arg);
    }
}
