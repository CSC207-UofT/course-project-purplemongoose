package database.SQLite.commands;

import database.SQLite.SQLiteDataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class describing an SQL statement to call and update to the database.
 */
public abstract class SQLiteQuery extends SQLiteStatement{
    private ResultSet results;

    /**
     * Execute a query and store the results.
     *
     * @param dataBase the database to execute the statement on.
     * @throws SQLException if there was an error executing the query
     */
    @Override
    public void execute(SQLiteDataBase dataBase) throws SQLException {
        dataBase.executeQuery(statement, args);
    }

    /**
     * Get the results of the query into the database
     *
     * @return the results of the query
     */
    public ResultSet getResults() {
        return results;
    }
}
