package database.SQLite;

import java.sql.*;

/**
 * A wrapper class for the handling of an SQLite database connection.
 */
public abstract class SQLiteDataBase {
    protected Connection connection;

    /**
     * Establishes a connection between the local database and the program.
     */
    public abstract void open() throws SQLException;

    /**
     * Establishes a connection between the local database and the program.
     */
    public void close() throws SQLException {
        connection.close();
    }

    /**
     * Execute an SQL statement on the database.
     *
     * @param sqlStatement the string representing the statement to execute
     * @param args the arguments to pass into the prepared statement
     * @throws SQLException if the statement could not be processed
     */
    public void executeStatement(String sqlStatement, Object... args) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(sqlStatement);
        for (int i = 0; i < args.length; i++)
            statement.setObject(i, args[i]);

        statement.executeUpdate();
    }

    /**
     * Execute an SQL query on the database.
     *
     * @param sqlQuery the string representing the query to execute
     * @param args the arguments to pass into the prepared statement
     * @throws SQLException if the query could not be processed
     */
    public ResultSet executeQuery(String sqlQuery, Object... args) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        for (int i = 0; i < args.length; i++)
            statement.setObject(i, args[i]);

        return statement.executeQuery();
    }


    public static void main(String[] args) {
        try {
            SQLiteDataBase db = new SQLiteDataBaseMemory();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
