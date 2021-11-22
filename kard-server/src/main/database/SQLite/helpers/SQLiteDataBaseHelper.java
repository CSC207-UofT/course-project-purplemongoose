package database.SQLite.helpers;

import database.SQLite.SQLiteDataBase;
import database.SQLite.SQLiteDataBaseFile;
import database.SQLite.SQLiteDataBaseMemory;
import database.SQLite.commands.SQLiteStatement;

import java.io.IOException;
import java.sql.SQLException;

/**
 * An abstract class for interfacing with the SQLite database, through the SQLiteDataBase class.
 * Responsibilities are creation of the database object, and providing misc functionality to the database,
 * specific to the database in question.
 *
 * Note: Application specific uses of the database are supposed to interact with the gateway for the corresponding
 * database.
 */
public abstract class SQLiteDataBaseHelper {
    protected SQLiteDataBase dataBase;

    /**
     * Create an in-memory database for the helper
     */
    public SQLiteDataBaseHelper() {
        try {
            dataBase = new SQLiteDataBaseMemory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create an in-memory database for the database to reference
     *
     * @throws IOException if the file does not exist and createIfNotExists is false
     * @throws IOException if the directory for the file does not exist and could not be created
     */
    public SQLiteDataBaseHelper(String path, boolean createIfNotExists) throws IOException {
        try {
            dataBase = new SQLiteDataBaseFile(path, createIfNotExists);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes a statement on the SQLite database that the helper is responsible for
     *
     * @param statement the statement to execute
     * @return whether the statement could be executed properly
     */
    public boolean executeStatement(SQLiteStatement statement) {
        try {
            statement.execute(dataBase);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Execute all the statements in statements sequentially.
     * Note: the function does not early return idf a statement fails.
     *
     * @param statements the statements to execute.
     * @return whether the statements could be executed.
     */
    public boolean executeStatements(SQLiteStatement... statements) {
        boolean success = true;

        for (SQLiteStatement statement : statements)
            success = success && executeStatement(statement);

        return success;
    }
}
