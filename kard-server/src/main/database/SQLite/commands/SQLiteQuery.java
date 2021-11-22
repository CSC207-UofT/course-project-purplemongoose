package database.SQLite.commands;

import database.SQLite.SQLiteDataBase;
import database.SQLite.arguments.SQLiteArg;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class describing an SQL statement to call and update to the database.
 */
public abstract class SQLiteQuery extends SQLiteStatement{
    private ResultSet results;

    /**
     * Creates an SQLQuery, with arguments args.
     *
     * @param statement the sqlite statement.
     * @param args      the args to pass in to the statement
     */
    public SQLiteQuery(String statement, SQLiteArg... args) {
        super(statement, args);
    }

    /**
     * Execute a query and store the results.
     *
     * @param dataBase the database to execute the statement on.
     * @throws SQLException if there was an error executing the query
     */
    @Override
    public void execute(SQLiteDataBase dataBase) throws SQLException {
        this.results = dataBase.executeQuery(statement, args);
    }

    /**
     * Get the results of the query into the database
     *
     * @return the results of the query
     */
    protected ResultSet getResults() {
        return results;
    }
}
