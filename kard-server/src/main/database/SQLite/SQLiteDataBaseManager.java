package database.SQLite;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * A class for getting connections to databases
 */
public class SQLiteDataBaseManager {
    private static final HashMap<String, SQLiteDataBase> connections = new HashMap<>();


    public static SQLiteDataBase getDatabase(String path, boolean createIfNotExists) throws SQLException, IOException {
        if (connections.containsKey(path))
            return connections.get(path);

        SQLiteDataBase database = new SQLiteDataBaseFile(path, createIfNotExists);
        connections.put(path, database);
        return database;
    }

    public static void close() {
        for (String key : connections.keySet()) {
            try {
                connections.get(key).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
