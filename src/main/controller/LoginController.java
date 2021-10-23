package controller;


import usecase.CreateAccount;
import usecase.LoginAuth;
import view.ViewModel;

public class LoginController {

    public Object submitLogin(Object login) {
        LoginAuth auth = new LoginAuth();

        if (auth.requestLogin(login.username, login.password)) {
            return new ViewModel(loginSuccess());
        }
        else {
            return new ViewModel(loginFailed());
        }
    }

    public Object submitSignUp(Object signup) {
        CreateAccount creator = new CreateAccount();

        if (creator.generateUser(signup.username, signup.password)) {
            return new ViewModel(signupSuccess());
        }
        else {
            return new ViewModel(signupFailed());
        }
    }
}

