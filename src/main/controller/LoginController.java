package controller;


import usecase.CreateAccount;
import usecase.LoginAuth;
import view.ViewModel;

public class LoginController {

    public Object submitLogin(Object login) {
        LoginAuth auth = new LoginAuth();

        if (auth.requestLogin("login.username", "login.password")) {
            return 1; //new ViewModel(success());
        }
        else {
            return 0; //new ViewModel(failure());
        }
    }

    public Object submitSignUp(Object signup) {
        CreateAccount creator = new CreateAccount();

        if (creator.generateUser("signup.username", "signup.password")) {
            return 1; //new ViewModel(success());
        }
        else {
            return 0; //new ViewModel(failure());
        }
    }
}

