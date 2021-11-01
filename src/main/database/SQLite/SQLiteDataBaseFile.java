package database.SQLite;

import java.io.*;
import java.sql.*;

/**
 * A wrapper class for the handling of an SQLite database connection from a file.
 */
public class SQLiteDataBaseFile extends SQLiteDataBase {
    private String path;

    /**
     * Creates a SQLiteDataBase object, of the database located at path
     *
     * @param path The file path to the SQLiteDataBase
     * @throws FileNotFoundException if the file specified by the path does not exist.
     */
    public SQLiteDataBaseFile(String path, boolean createIfNotExists) throws FileNotFoundException, SQLException{
        this.path = path;
        if(dbNotExists() && !createIfNotExists){
            throw new FileNotFoundException(
                    "Could not open SQLite database, the file located at "
                            + this.path + " does not exist."
            );
        }

        open();
    }

    /**
     * Returns whether the database exists.
     *
     * @return whether the file located at the path of the database exists.
     */
    private boolean dbNotExists() {
        File f = new File(path);

        return !f.exists();
    }

    @Override
    public void open() throws SQLException {
        connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", path));
    }

    /**
     * Sets the path for the database.
     *
     * @param path The file path to the SQLiteDataBase
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * @param path The file path to the SQLiteDataBase
     */
    public void reopen(String path, boolean createIfNotExists) throws FileNotFoundException, SQLException {
        setPath(path);
        if(dbNotExists() && !createIfNotExists){
            throw new FileNotFoundException(
                    "Could not open SQLite database, the file located at "
                            + this.path + " does not exist."
            );
        }

        reopen();
    }
}
