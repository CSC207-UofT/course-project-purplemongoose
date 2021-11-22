package database.SQLite.helpers;

import database.SQLite.commands.SQLiteCreateAccountsTableStatement;
import database.SQLite.commands.SQLiteCreateProfilesTableStatement;

import java.io.IOException;


/**
 * A class for the handling of the mainframe database.
 */
public class SQLiteDataBaseHelperMainFrame extends SQLiteDataBaseHelper{

    /**
     * Set up the database
     */
    private void setup() {
        executeStatements(
                new SQLiteCreateAccountsTableStatement(),
                new SQLiteCreateProfilesTableStatement()
        );
    }

    /**
     * Create a reference to the mainframe located at location
     *
     * @param path the path to the mainframe database
     *
     * @throws IOException if the directory for the file does not exist and could not be created
     */
    public SQLiteDataBaseHelperMainFrame(String path) throws IOException {
        super(path, true);
        setup();
    }

    /**
     * Create an in-memory database for the mainframe
     */
    public SQLiteDataBaseHelperMainFrame() {
        super();
        setup();
    }

}
