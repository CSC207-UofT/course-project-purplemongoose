package database;

import java.io.*;
import java.sql.*;

public class MainFrameGateway {

    public MainFrameGateway() {
    }

    public static Connection mfConnect() {
        String mfLocation = "data/mainframe.db";
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
    private static void createTable(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            String tableSQL = """
                        CREATE TABLE IF NOT EXISTS "users" (
                        	"username"	TEXT NOT NULL UNIQUE,
                        	"password"	TEXT NOT NULL,
                        	"data"      BLOB
                        );""";
            stmt.execute(tableSQL);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static byte[] toBytes(Object object) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
            objOut.writeObject(object);
            return byteOut.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object toObject(byte[] data) {
        try {
            ByteArrayInputStream byteIn = new ByteArrayInputStream(data);
            ObjectInputStream objIn = new ObjectInputStream(byteIn);
            return objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}