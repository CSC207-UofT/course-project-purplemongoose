package database;

public class AccountGateway extends MainFrameGateway {
    private AccountGateway() {
        super("some/mf/location");
    }
    public static boolean generateUser(String username, String password) {
        // some sqlite code here
        return username.equals(password);
    }
}
