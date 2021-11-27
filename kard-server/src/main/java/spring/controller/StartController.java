package spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import usecase.account.CreateAccount;
import usecase.start.AuthLogin;
import dto.StartRequest;
import dto.ResponseContainer;

@RestController
@RequestMapping("/start")
public class StartController {
    AuthLogin auth = new AuthLogin(false);
    CreateAccount createAccount = new CreateAccount(false);

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
        ResponseContainer response = new ResponseContainer();
        if (auth.requestLogin(request.getAccountUsername(), request.getAccountPassword())) {
            response.add(true);
        } else {
            response.add(false);
            response.setError("5"); // login attempt failed
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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
        ResponseContainer response = new ResponseContainer();
        if (createAccount.newPersonalAccount(request.getAccountUsername(), request.getAccountPassword())) {
            response.add(true);
        } else {
            response.add(false);
            response.setError("6"); // username has already been taken
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
