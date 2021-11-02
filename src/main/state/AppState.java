package state;

// saves the username for use in various use cases
public class AppState {
    private static String currentUsername = null;

    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static void setCurrentUsername(String uuid) {
        currentUsername = uuid;
    }
}
