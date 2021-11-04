package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import usecase.AccountUseCases;
import usecase.LoginAuth;
import viewmodel.StartRequest;
import viewmodel.ResponseContainer;
import viewmodel.ShortResponse;

@RestController
@RequestMapping("/start")
public class LoginController {
    LoginAuth auth = new LoginAuth();
    AccountUseCases accUseCase = new AccountUseCases();

    /**
     * Takes in a StartRequest object and authenticates the information provided. If either the username or password is
     * wrong, then a 'false' response is sent back. Otherwise, a 'true' response is sent back.
     *
     * @param request JSON converted into StartRequest which contains the username and password
     * @return return a ResponseEntity which contains a 'true'/'false' response and an HTTP status code
     */
    @PostMapping(path="/login", consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitLogin(@RequestBody StartRequest request) {
        ShortResponse response = new ShortResponse();
        if (this.auth.requestLogin(request.getAccountUsername(), request.getAccountPassword())) {
            response.add(true);
        }
        else {
            response.add(false);
            response.setError(9); // login attempt failed
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

<<<<<<< HEAD
    @GetMapping("/signup")
    public boolean submitSignUp(@RequestParam(value = "username", defaultValue = "") String username,
                                @RequestParam(value = "password", defaultValue = "") String password) {
        System.out.println(username + " p:" + password);
        return this.accUseCase.createNewAccount(username, password);
=======
    /**
     * Takes in a LoginRequest object and authenticates the attempts to make a new account with the given information.
     * If the username is already taken, then a 'false' response is sent back. Otherwise, a 'true' response is sent.
     *
     * @param request JSON converted into StartRequest which contains the username and password
     * @return return a ResponseEntity which contains a 'true'/'false' response and an HTTP status code
     */
    @PostMapping(path="/signup", consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitSignUp(@RequestBody StartRequest request) {
        ShortResponse response = new ShortResponse();
        if (this.accUseCase.createNewAccount(request.getAccountUsername(), request.getAccountPassword())) {
            response.add(true);
        }
        else {
            response.add(false);
            response.setError(10); // username has already been taken
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
>>>>>>> 4d25b569e67152e444dbea916ed9fa4bf066f1b6
    }
}