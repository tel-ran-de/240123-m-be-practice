package de.telran.bank.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AccountController {
    @Autowired
    private AccountStorage storage;

    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    @PostMapping(value = "/account", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void createAccount(@RequestBody Account account) {
        storage.save(account);
        LOG.info("Received account = {}", account);
    }

    @GetMapping("/account/{id}")
    public Account getAccount(@PathVariable UUID id) {
        return storage.get(id);
    }
}
