package database;

import java.io.*;
import java.sql.*;

/**
 * A wrapper class for the handling of an SQLite database.
 */
public class SQLiteDataBase {
    private String PATH;
    private Connection connection;

    /**
     * Creates a SQLiteDataBase object, of the database located at path
     *
     * @param path The file path to the SQLiteDataBase
     */
    public SQLiteDataBase(String path) throws FileNotFoundException, SQLException {
        this.PATH = path;
        open();
    }

    /**
     * A method for establishing a connection between the database and the program.
     *
     * @throws FileNotFoundException if the file specified by the path does not exist.
     */
    private void open() throws FileNotFoundException, SQLException {
        File file = new File(PATH);

        if (!file.exists()) {
            throw new FileNotFoundException(
                "Could not open SQLite database, the file located at "
                + PATH + " does not exist."
            );
        }
        else {
            connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", PATH));
        }

    }

}
