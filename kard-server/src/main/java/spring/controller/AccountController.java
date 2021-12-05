package spring.controller;

import dto.CreateAccountRequest;
import dto.DeleteAccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dto.ResponseContainer;
import usecase.account.*;

/*
 * A connection point between the external user interfaces and the back end of kard
 *
 * New user interfaces should use HTTP to access the Spring server here
 *
 * When connecting with one of the controllers in this class, ensure that the
 * appropriate kind of HTTP request (GET or POST) is being used. These will be specified in the
 * javadoc comments fo the individual controller.
 */
@RestController
@RequestMapping("account")

public class AccountController {
    /**
     * Defines methods for interactions between a user's profile and the other users in their contacts
     */
    CreateAccount createAccount = new CreateAccount(false);
    DeleteAccount deleteAccount = new DeleteAccount(false);


    /**
     * Takes in a LoginRequest object and authenticates the attempts to make a new account with the given information.
     * If the username is already taken, then a 'false' response is sent back. Otherwise, a 'true' response is sent.
     *
     * @param request JSON converted into StartRequest which contains the username and password
     * @return return a ResponseEntity which contains a 'true'/'false' response and an HTTP status code
     */
    @PostMapping(path="/create",
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitPersonalAccountCreate(
            @RequestBody CreateAccountRequest request) {
        ResponseContainer response = new ResponseContainer();
        if (createAccount.newPersonalAccount(request.getAccountUsername(), request.getAccountPassword())) {
            response.add(true);
        } else {
            response.add(false);
            response.setError("100"); // username has already been taken
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
    @PostMapping(path="/delete",
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitAccountDelete(@RequestBody DeleteAccountRequest request) {
        ResponseContainer response = new ResponseContainer();
        if (deleteAccount.delete(request.getAccountUsername())) {
            response.add(true);
        } else {
            response.add(false);
            response.setError("101");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
