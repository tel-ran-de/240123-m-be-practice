package de.telran.bank.account;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AccountStorage {
    private final Map<UUID, Account> accounts = new ConcurrentHashMap<>();
    public void save(Account account) throws DuplicatedAccountExeption {
       Account accountResult =  accounts.compute(account.getUuid(),(key, prevAccount) -> {
            if (prevAccount == null) {
                return account;
            }
            if (prevAccount.equals(account)) {
                return account;
            }
            return prevAccount;
        });
        if (!account.equals(accountResult)) {
            throw new DuplicatedAccountExeption();
        }
    }
    public Account get(UUID id) {
        return accounts.get(id);
    }
}
