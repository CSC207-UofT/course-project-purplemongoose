package database.SQLite;

import java.io.*;
import java.sql.*;

/**
 * A wrapper class for the handling of an SQLite database connection from a file.
 */
public class SQLiteDataBaseFile extends SQLiteDataBase {
    private final String path;

    /**
     * Creates a SQLiteDataBase object, of the database located at path
     *
     * @param path The file path to the SQLiteDataBase
     * @throws FileNotFoundException if the file specified by the path does not exist.
     */
    public SQLiteDataBaseFile(String path, boolean createIfNotExists) throws FileNotFoundException {
        this.path = path;
        if(!dbExists() && !createIfNotExists){
            throw new FileNotFoundException(
                    "Could not open SQLite database, the file located at "
                            + this.path + " does not exist."
            );
        }
    }

    /**
     * Returns whether the database exists.
     *
     * @return whether the file located at the path of the database exists.
     */
    public boolean dbExists() {
        File f = new File(path);

        return f.exists();
    }

    @Override
    public Connection open() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", path));

        return connection;
    }
}
