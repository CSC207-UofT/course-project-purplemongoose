package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import usecase.AccountUseCases;
import usecase.LoginAuth;
import viewmodel.SimpleResponse;
import viewmodel.Container;

@RestController
@RequestMapping("/start")
public class LoginController {
    LoginAuth auth = new LoginAuth();
    AccountUseCases accUseCase = new AccountUseCases();

    @GetMapping(path="/login", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Container> submitLogin(@RequestParam(value = "username", defaultValue = "") String username,
                                      @RequestParam(value = "password", defaultValue = "") String password) {
        SimpleResponse response = new SimpleResponse();
        if (this.auth.requestLogin(username, password)) {
            response.add(true);
        }
        else {
            response.add(false);
            response.setError(9); // placeholder for now, list of proper error codes will be made later
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path="/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Container> submitSignUp(@RequestParam(value = "username", defaultValue = "") String username,
                                @RequestParam(value = "password", defaultValue = "") String password) {
        SimpleResponse response = new SimpleResponse();
        if (this.accUseCase.createNewAccount(username, password)) {
            response.add(true);
        }
        else {
            response.add(false);
            response.setError(10);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
        // may use more descriptive response in the future - e.g. report back the specific reason
    }
}