package database.SQLite.commands;

import database.SQLite.SQLiteDataBase;
import database.SQLite.arguments.SQLiteArg;

import java.sql.SQLException;

/**
 * A class describing an SQL statement to call and update to the database.
 */
public abstract class SQLiteStatement {
    protected final String statement;
    protected final SQLiteArg[] args;

    /**
     * Creates an SQLStatement, with arguments args
     *
     * @param statement the sqlite statement.
     * @param args the args to pass in to the statement
     */
    public SQLiteStatement(String statement, SQLiteArg... args){
        this.statement = statement;
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
