package de.telran.bank.wallet;

import de.telran.bank.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface WalletRepository extends CrudRepository<WalletEntity, UUID> {

    List<WalletEntity> findByAccountId(UUID accountId);
}
