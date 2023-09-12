package de.telran.bank.account;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AccountController {

    @Autowired
    private AccountStorage storage;

    @Value("${secretKey}")
    private String secretKey;

    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    @PostMapping(value = "/account", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void createAccount(@RequestBody AccountJson accountJson) throws DuplicatedAccountException {
        LOG.info("Received account = {}", accountJson);
        storage.save(accountJson);
    }

    @PutMapping(value = "/account", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void updateAccount(@RequestBody AccountJson accountJson, HttpServletRequest request) throws SecurityCheckException {
        LOG.info("Received account = {}", accountJson);
        String secretKey = request.getHeader("X-Secret-Key");
        if (!this.secretKey.equals(secretKey)) {
            throw new SecurityCheckException();
        }

        storage.update(accountJson);
    }


    @GetMapping("/account/{id}")
    public AccountJson getAccount(@PathVariable UUID id) {
        return storage.get(id);
    }
}
