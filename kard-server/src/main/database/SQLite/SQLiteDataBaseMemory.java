package database.SQLite;

import java.sql.*;

/**
 * A wrapper class for the handling of an SQLite database connection in memory.
 *
 * @see SQLiteDataBase
 */
public class SQLiteDataBaseMemory extends SQLiteDataBase {

    /**
     * Represents a database in memory.
     *
     * @throws SQLException if the database could not be created/accessed
     */
    public SQLiteDataBaseMemory() throws SQLException{
    }

    @Override
    public void open() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");
    }



}
