package database;

import java.io.File;
import java.sql.*;

public abstract class MainFrameGateway {

    private String mfLocation;

    public MainFrameGateway() {
        this.mfLocation = "data/mainframe.db";
    }

    public Connection mfConnect() {
        File file = new File(mfLocation);
        Connection conn = null;

        if (file.exists()) {
            try {
                conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", mfLocation));
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        else { // if such a db doesn't exist, create one and add a table
            try {
                conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", mfLocation));
                createTable(conn);
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return conn; // return a connection for other methods to use
    }

    // create table with username and password fields
    private void createTable(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            String tableSQL = """
                        CREATE TABLE IF NOT EXISTS "users" (
                        	"username"	TEXT NOT NULL UNIQUE,
                        	"password"	TEXT NOT NULL
                        );""";
            stmt.execute(tableSQL);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}