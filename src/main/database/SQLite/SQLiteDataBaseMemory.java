package database.SQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A wrapper class for the handling of an SQLite database connection in memory.
 */
public class SQLiteDataBaseMemory extends SQLiteDataBase {

    /**
     * Represents a database in memory.
     */
    public SQLiteDataBaseMemory(){
    }

    @Override
    public Connection open() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");

        return connection;
    }



}
