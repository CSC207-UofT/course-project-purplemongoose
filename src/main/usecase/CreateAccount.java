package usecase;

import database.AccountGateway;

public class CreateAccount {
    public boolean generateUser(String username, String password) {
        return AccountGateway.generateUser(username, password);
    }
}
