package spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import usecase.account.CreateAccount;
import usecase.start.AuthLogin;
import dto.LoginRequest;
import dto.ResponseContainer;

@RestController
@RequestMapping("kard")
public class LoginController {
    AuthLogin auth = new AuthLogin(false);

    /**
     * Takes in a StartRequest object and authenticates the information provided. If either the username or password is
     * wrong, then a 'false' response is sent back. Otherwise, a 'true' response is sent back.
     *
     * @param request JSON converted into StartRequest which contains the username and password
     * @return return a ResponseEntity which contains a 'true'/'false' response and an HTTP status code
     */
    @PostMapping(path="/login",
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitLogin(@RequestBody LoginRequest request) {
        ResponseContainer response = new ResponseContainer();
        if (auth.requestLogin(request.getAccountUsername(), request.getAccountPassword())) {
            response.add(true);
        } else {
            response.add(false);
            response.setError("105");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
