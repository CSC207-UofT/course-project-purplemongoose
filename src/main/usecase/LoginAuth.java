package usecase;

import database.AuthGateway;

public class LoginAuth {
    AuthGateway gw = new AuthGateway();

    public boolean requestLogin(String username, String password) {
        return this.gw.authLoginInfo(username, password);
    }

}
