package database.SQLite.commands;

import database.SQLite.SQLiteDataBase;

import java.sql.SQLException;

/**
 * A class describing an SQL statement to call and update to the database.
 */
public class SQLiteStatement {
    protected String statement = "";
    private final Object[] args;

    /**
     * Creates an SQLStatement, with arguments args
     *
     * @param args the args to pass in to the statement
     */
    public SQLiteStatement(Object... args){
        this.args = args;
    }

    /**
     * Execute the statement on a given database
     *
     * @param dataBase the database to execute the statement on.
     * @throws SQLException if there was an error executing the statement
     */
    public void execute(SQLiteDataBase dataBase) throws SQLException {
        dataBase.executeStatement(statement, args);
    }
}
