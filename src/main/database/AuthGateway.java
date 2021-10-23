package database;

public final class AuthGateway extends MainFrameGateway {

    private AuthGateway() {
        super("mf/location/on/disk/");
    }

    // return value subject to change
    public static boolean checkLoginInfo(String username, String password) {
        // some code here to connect to sqlite db
        return username.equals(password);
    }
}
