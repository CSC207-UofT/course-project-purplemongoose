package database.SQLite;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A wrapper class for the handling of an SQLite database connection in memory.
 */
public class SQLiteDataBaseMemoryConnection extends SQLiteDataBaseConnection{

    /**
     * Represents a database in memory.
     */
    public SQLiteDataBaseMemoryConnection(){
    }

    @Override
    public Connection open() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");

        return connection;
    }



}
