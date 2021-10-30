package controller;

import usecase.AccountUseCases;
import usecase.LoginAuth;

public class LoginController {
    LoginAuth auth = new LoginAuth();
    AccountUseCases accUseCase = new AccountUseCases();


    public Object submitLogin(String username, String password) {

        if (this.auth.requestLogin(username, password)) {
            return 1;
        }
        else {
            return 0;
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

