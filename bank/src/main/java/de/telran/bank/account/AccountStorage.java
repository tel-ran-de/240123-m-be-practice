package de.telran.bank.account;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AccountStorage {

    private final Map<UUID, AccountJson> accounts = new ConcurrentHashMap<>();

    public void save(AccountJson accountJson) throws DuplicatedAccountException {
        AccountJson savedEntity = accounts.compute(accountJson.getUuid(), (key, prev) -> {
            if (prev == null || prev.equals(accountJson)) {
                return accountJson;
            } else {
                return prev;
            }
        });
        if (accountJson != savedEntity) {
            throw new DuplicatedAccountException();
        }
    }

    public AccountJson get(UUID id) {
        return accounts.get(id);
    }

    public void update(AccountJson accountJson) {
        accounts.put(accountJson.getUuid(), accountJson);
    }
}
