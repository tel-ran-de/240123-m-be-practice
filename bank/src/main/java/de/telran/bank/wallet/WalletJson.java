package de.telran.bank.wallet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.UUID;

public class WalletJson {
    @JsonProperty
    private UUID id;

    @JsonProperty
    private String currencyCode;

    @JsonProperty
    private UUID accountId;

    @JsonProperty
    private BigDecimal balance;

    @JsonCreator
    public WalletJson(UUID id, String currencyCode, UUID accountId, BigDecimal balance) {
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
}
