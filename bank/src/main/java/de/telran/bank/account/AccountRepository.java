package de.telran.bank.account;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccountRepository extends CrudRepository<AccountEntity, UUID> {
}
