package usecase;

import database.AuthGateway;

public class LoginAuth {
    public boolean requestLogin(String username, String password) {
        return AuthGateway.checkLoginInfo(username, password);
    }

}
