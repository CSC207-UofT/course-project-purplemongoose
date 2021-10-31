package controller;

import ViewModel.StartingViewModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import usecase.AccountUseCases;
import usecase.LoginAuth;

@RestController
@RequestMapping("start")
public class LoginController {
    LoginAuth auth = new LoginAuth();
    AccountUseCases accUseCase = new AccountUseCases();

    @GetMapping("/login")
    public StartingViewModel submitLogin(@RequestParam(value = "username", defaultValue = "") String username,
                               @RequestParam(value = "password", defaultValue = "") String password) {
        if (this.auth.requestLogin(username, password)) {
            return new StartingViewModel(true);
        }
        else {
            return new StartingViewModel(false);
        }
    }

    @GetMapping("/signup")
    public boolean submitSignUp(@RequestParam(value = "username", defaultValue = "") String username,
                                @RequestParam(value = "password", defaultValue = "") String password) {
        return this.accUseCase.createNewAccount(username, password);
    }
}
