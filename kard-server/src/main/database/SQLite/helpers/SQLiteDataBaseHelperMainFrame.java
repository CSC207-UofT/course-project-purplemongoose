package database.SQLite.helpers;

import database.SQLite.SQLiteDataBaseFile;
import database.SQLite.commands.SQLiteCreateAccountsTableStatement;
import database.SQLite.commands.SQLiteCreateProfilesTableStatement;

import java.io.IOException;
import java.sql.SQLException;

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
     */
    public SQLiteDataBaseHelperMainFrame(String path) {
        try {
            this.dataBase = new SQLiteDataBaseFile("./data/MainFrame.db", true);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        setup();
    }

}
