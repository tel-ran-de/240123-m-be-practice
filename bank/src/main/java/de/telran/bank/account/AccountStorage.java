package de.telran.bank.account;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AccountStorage {

    private final Map<UUID, Account> accounts = new ConcurrentHashMap<>();
    public void save(Account account) {
        accounts.put(account.getUuid(), account);
    }
    public Account get(UUID id) {
        return accounts.get(id);
    }
}
