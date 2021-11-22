package database.SQLite;

import database.SQLite.arguments.SQLiteArg;

import java.io.IOException;
import java.sql.*;

/**
 * A wrapper class for the handling of an SQLite database connection.
 */
public abstract class SQLiteDataBase {
    protected Connection connection;

    /**
     * Establishes a connection between the local database and the program.
     *
     * @throws SQLException if the program fails to open the connection
     */
    public abstract void open() throws SQLException;

    /**
     * Closes the existing connection, then establishes a new one.
     *
     * @throws SQLException if the program fails to close/open the connection
     */
    public void reopen() throws SQLException {
        close(); open();
    }

    /**
     * Closes the connection between the local database and the program.
     *
     * @throws SQLException if the program fails to close the connection
     */
    public void close() throws SQLException {
        if (connection != null) connection.close();
    }

    /**
     * Execute an SQL statement on the database.
     *
     * @param sqlStatement the string representing the statement to execute
     * @param args the arguments to pass into the prepared statement
     * @throws SQLException if the statement could not be processed
     */
    public void executeStatement(String sqlStatement, SQLiteArg... args) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(sqlStatement);
        for (int i = 0; i < args.length; i++)
            args[i].setArg(i + 1, statement);

        statement.executeUpdate();
    }

    /**
     * Execute an SQL query on the database.
     *
     * @param sqlQuery the string representing the query to execute
     * @param args the arguments to pass into the prepared statement
     * @throws SQLException if the query could not be processed
     */
    public ResultSet executeQuery(String sqlQuery, SQLiteArg... args) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        for (int i = 0; i < args.length; i++)
            args[i].setArg(i + 1, statement);

        return statement.executeQuery();
    }

}
