import controller.SpringServerController;
import database.SQLite.SQLiteDataBaseManager;

public class Main {

    // Starts a new server instance on this machine
    public static void main(String[] args) {
        SpringServerController server = new SpringServerController();
        server.start(args);

        // Important to  close all the open databases.
        SQLiteDataBaseManager.close();
    }

}
