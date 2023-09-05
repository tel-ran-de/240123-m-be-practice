package de.telran.bank.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private Logger log = LoggerFactory.getLogger(AccountController.class);

    @PostMapping(value = "/account", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void createAccount(@RequestBody Account account) {
        log.info("Received account = {}", account);
    }

    @GetMapping("/account")
    public Account getAccount() {
        return new Account("Anton", "Ermak");
    }
}
