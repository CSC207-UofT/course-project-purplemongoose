package database.SQLite;

import java.sql.*;

/**
 * A wrapper class for the handling of an SQLite database connection.
 */
public abstract class SQLiteDataBaseConnection {

    /**
     * Establishes a connection between the local database and the program.
     *
     * @return a connection to the database represented by the class
     */
    public abstract Connection open() throws SQLException;

    public static void main(String[] args) {
        try {
            SQLiteDataBaseConnection db = new SQLiteDataBaseMemoryConnection();

            db.open();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
