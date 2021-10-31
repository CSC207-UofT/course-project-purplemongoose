package state;

// effectively keeps important data as global variables
public class AppState {
    private static String currentUUID = null;

    public static String getCurrentUUID() {
        return currentUUID;
    }

    public static void setCurrentUUID(String uuid) {
        currentUUID = uuid;
    }


}
