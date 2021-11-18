package database.SQLite.commands;

/**
 * Creates the accounts table for the mainframe
 *
 * @see SQLiteStatement
 */
public class SQLiteCreateAccountsTableStatement extends SQLiteStatement{

    /**
     * Creates a statement to update the MainFrame with adding the accounts table if it doesn't exist
     */
    public SQLiteCreateAccountsTableStatement() {
        this.statement = """
                    CREATE TABLE IF NOT EXISTS "accounts" (
                    	"username"	TEXT NOT NULL UNIQUE,
                    	"password"	TEXT NOT NULL,
                    	"account"   BLOB
                    );""";
    }
}
