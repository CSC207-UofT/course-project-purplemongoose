package controller;

import usecase.AccountUseCases;
import usecase.LoginAuth;

public class LoginController {
    LoginAuth auth = new LoginAuth();
    AccountUseCases accUseCase = new AccountUseCases();


    public boolean submitLogin(String username, String password) {

        if (this.auth.requestLogin(username, password)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean submitSignUp(String username, String password) {

        if (this.accUseCase.createNewAccount(username, password)) {
            return true;
        }
        else {
            return false;
        }
    }
}

