package de.telran.bank.account;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AccountStorage {

    private final Map<UUID, Account> accounts = new ConcurrentHashMap<>();

    public void save(Account account) throws DuplicatedAccountException {
        Account savedEntity = accounts.compute(account.getUuid(), (key, prev) -> {
            if (prev == null || prev.equals(account)) {
                return account;
            } else {
                return prev;
            }
        });
        if (account != savedEntity) {
            throw new DuplicatedAccountException();
        }
    }

    public Account get(UUID id) {
        return accounts.get(id);
    }

    public void update(Account account) {
        accounts.put(account.getUuid(), account);
    }
}
