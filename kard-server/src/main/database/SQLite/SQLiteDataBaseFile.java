package database.SQLite;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

/**
 * A wrapper class for the handling of an SQLite database connection from a file.
 *
 * @see SQLiteDataBase
 */
public class SQLiteDataBaseFile extends SQLiteDataBase {
    private String path;

    /**
     * Creates a SQLiteDataBase object, of the database located at path
     *
     * @param path The file path to the SQLiteDataBase
     * @throws FileNotFoundException if the file specified by the path does not exist.
     */
    public SQLiteDataBaseFile(String path, boolean createIfNotExists) throws IOException, SQLException{
        setPath(path, createIfNotExists);
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

    /**
     * Create the directory at path, if it doesn't already exist.
     */
    private void makeDbPathIfNotExists() throws IOException {
        Path pathToDB = new File(path).toPath().normalize().getParent();

        if (Files.exists(pathToDB)) return;
        Files.createDirectory(pathToDB);
    }

    @Override
    public void open() throws SQLException {
        connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", path));
    }

    /**
     * Sets the path for the database.
     *
     * @param path The file path to the SQLiteDataBase
     * @throws IOException if the file does not exist and createIfNotExists is false
     * @throws IOException if the directory for the file does not exist and could not be created
     */
    public void setPath(String path, boolean createIfNotExists) throws IOException {
        if(dbNotExists() && !createIfNotExists){
            throw new FileNotFoundException(
                    "Could not open SQLite database, the file located at "
                            + this.path + " does not exist."
            );
        }
        makeDbPathIfNotExists();

        this.path = path;
    }

    /**
     *  Reopen the database to a new file.
     *
     * @param path The file path to the SQLiteDataBase
     * @throws IOException if the file does not exist and createIfNotExists is false
     * @throws IOException if the directory for the file does not exist and could not be created
     */
    public void reopen(String path, boolean createIfNotExists) throws IOException, SQLException {
        setPath(path, createIfNotExists);

        reopen();
    }
}
