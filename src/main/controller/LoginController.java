package controller;


import usecase.AccountUseCases;
import usecase.LoginAuth;

public class LoginController {
    LoginAuth auth = new LoginAuth();
    AccountUseCases accUseCase = new AccountUseCases();


    public Object submitLogin(String username, String password) {
        LoginAuth auth = new LoginAuth();

        if (auth.requestLogin(username, password)) {
            return 1; //new ViewModel(success());
        }
        else {
            return 0; //new ViewModel(failure());
        }
    }

    public Object submitSignUp(Object signup) {
        AccountUseCases acc = new AccountUseCases();

        if (acc.createNewAccount("signup.username", "signup.password")) {
            return 1; //new ViewModel(success());
        }
        else {
            return 0; //new ViewModel(failure());
        }
    }
}

