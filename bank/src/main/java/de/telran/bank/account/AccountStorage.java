package de.telran.bank.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class AccountStorage {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void save(AccountEntity accountEntity) throws DuplicatedAccountException {
        AccountEntity prev = get(accountEntity.getId());
        if (prev != null) {
            throw new DuplicatedAccountException();
        }
        accountRepository.save(accountEntity);
    }

    public AccountEntity get(UUID id) {
        return accountRepository.findById(id).orElse(null);
    }

    public void update(AccountEntity accountEntity) {
        accountRepository.save(accountEntity);
    }
}
