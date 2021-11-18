package database.SQLite.helpers;

import database.SQLite.SQLiteDataBase;
import database.SQLite.SQLiteDataBaseFile;
import database.SQLite.SQLiteDataBaseMemory;

import java.io.IOException;
import java.sql.SQLException;

/**
 * An abstract class for interfacing with the SQLite database, through the SQLiteDataBase class.
 */
public abstract class SQLiteDataBaseHelper {
    private SQLiteDataBase dataBase;

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

    
}
