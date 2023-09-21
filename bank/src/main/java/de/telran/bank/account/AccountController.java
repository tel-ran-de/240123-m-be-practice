package de.telran.bank.account;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AccountController {

    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountManagementService accountManagementService;

    @Value("${secretKey}")
    private String secretKey;

    @PostMapping(value = "/account", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void createAccount(@Valid @RequestBody AccountJson accountJson) throws DuplicatedEntityException {
        LOG.info("Received account = {}", accountJson);
        accountManagementService.save(new AccountEntity(accountJson.getUuid(),
                accountJson.getFirstName(),
                accountJson.getLastName()));
    }

    @PutMapping(value = "/account", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void updateAccount(@Valid @RequestBody AccountJson accountJson, HttpServletRequest request) throws SecurityCheckException {
        LOG.info("Received account = {}", accountJson);
        String secretKey = request.getHeader("X-Secret-Key");
        if (!this.secretKey.equals(secretKey)) {
            throw new SecurityCheckException();
        }

        accountManagementService.update(new AccountEntity(accountJson.getUuid(),
                accountJson.getFirstName(),
                accountJson.getLastName()));
    }


    @GetMapping("/account/{id}")
    public AccountJson getAccount(@PathVariable UUID id) {
        AccountEntity accountEntity = accountManagementService.get(id);
        if (accountEntity == null) {
            return null;
        }
        return new AccountJson(accountEntity.getId(), accountEntity.getFirstName(), accountEntity.getLastName());
    }
}
