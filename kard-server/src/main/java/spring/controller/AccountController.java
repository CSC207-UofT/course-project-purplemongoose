package spring.controller;

import dto.AccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dto.ResponseContainer;
import usecase.account.*;

/**
 * Defines methods for interacting with an account
 */
@RestController
@RequestMapping("account")
public class AccountController {
    CreateAccount createAccount = new CreateAccount(false);

    /**
     * Takes in a LoginRequest object and authenticates the attempts to make a new account with the given information.
     * If the username is already taken, then a 'false' response is sent back. Otherwise, a 'true' response is sent.
     *
     * Error code: 0 - by default is ok
     * Error code: 100 - username is already in use
     *
     * @param request JSON converted into StartRequest which contains the username and password
     * @return return a JSON object containing a 'true'/'false' response and an HTTP status code
     */
    @PostMapping(path="/create",
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitPersonalAccountCreate(
            @RequestBody AccountRequest request) {
        ResponseContainer response = new ResponseContainer();
        if (createAccount.newPersonalAccount(request.getAccountUsername(), request.getAccountPassword())) {
            response.add(true);
        } else {
            response.add(false);
            response.setError("100");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
