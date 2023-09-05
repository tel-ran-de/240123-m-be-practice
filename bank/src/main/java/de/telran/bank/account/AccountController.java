package de.telran.bank.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AccountController {

    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    @PostMapping(value = "/account", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void createAccount(@RequestBody Account account) {
        LOG.info("Received account = {}", account);
    }

    @GetMapping("/account")
    public Account getAccount() {
        return new Account(new UUID(5, 5), "Anton", "Ermak");
    }
}
