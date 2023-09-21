package de.telran.bank.wallet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class WalletEntity {
    @Id
    private UUID id;

    @Column
    private String currencyCode;

    @Column
    private UUID accountId;

    @Column
    private BigDecimal balance;

    public WalletEntity() {
    }

    public WalletEntity(UUID id, String currencyCode, UUID accountId, BigDecimal balance) {
        this.id = id;
        this.currencyCode = currencyCode;
        this.accountId = accountId;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "WalletEntity{" +
                "id=" + id +
                ", currencyCode='" + currencyCode + '\'' +
                ", accountId=" + accountId +
                ", balance=" + balance +
                '}';
    }
}
