package database.SQLite.commands;

/**
 * Creates the profiles table for the mainframe
 *
 * @see SQLiteStatement
 */
public class SQLiteCreateProfilesTableStatement extends SQLiteStatement {

    /**
     * Creates a statement to update the MainFrame with adding the accounts table if it doesn't exist
     */
    public SQLiteCreateProfilesTableStatement() {
        super("""
                        CREATE TABLE IF NOT EXISTS "profiles" (
                        	"username"  TEXT NOT NULL UNIQUE,
                        	"profile"   BLOB,
                        	"memento"   BLOB
                        );""");
    }
}
