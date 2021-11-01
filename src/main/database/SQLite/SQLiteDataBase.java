package database.SQLite;

import java.sql.*;

/**
 * A wrapper class for the handling of an SQLite database connection.
 */
public abstract class SQLiteDataBase {

    /**
     * Establishes a connection between the local database and the program.
     *
     * @return a connection to the database represented by the class
     */
    public abstract Connection open() throws SQLException;

    /**
     * Execute an SQL statement on the database.
     *
     * @param sqlStatement the string representing the statement to execute
     * @param args the arguments to pass into the prepared statement
     * @throws SQLException if the statement could not be processed
     */
    public void executeStatement(String sqlStatement, Object... args) throws SQLException{
        Connection connection = open();

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
        Connection connection = open();

        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        for (int i = 0; i < args.length; i++)
            statement.setObject(i, args[i]);

        return statement.executeQuery();
    }


    public static void main(String[] args) {
        try {
            SQLiteDataBase db = new SQLiteDataBaseMemory();

            Connection connection = db.open();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
