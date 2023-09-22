package de.telran.bank.wallet;

import de.telran.bank.account.DuplicatedEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class WalletManagementService {

    @Autowired
    private WalletRepository walletRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void save(WalletEntity walletEntity) throws DuplicatedEntityException {
        WalletEntity prev = get(walletEntity.getId());
        if (prev != null) {
            throw new DuplicatedEntityException();
        }
        walletRepository.save(walletEntity);
    }

    public WalletEntity get(UUID id) {
        return walletRepository.findById(id).orElse(null);
    }

    public List<WalletEntity> getWallets(UUID accountId) {

        return walletRepository.findByAccountId(accountId);
    }
}
