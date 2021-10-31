package usecase;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import state.AppState;
import database.AccountGateway;

public class LoginAuth {
    AccountGateway ag = new AccountGateway();

    @GetMapping("requestlogin")
    public boolean requestLogin(@RequestParam(value = "username", defaultValue = "") String username,
                                @RequestParam(value = "username", defaultValue = "") String password) {
        if (username.isBlank() || password.isBlank()) {
            return false;
        }
        else {
            String uuid = ag.authAccountData(username, password);
            if (uuid == null) {
                return false;
            }
            else {
                AppState.setCurrentUUID(uuid);
                return true;
            }
        }
    }
}
