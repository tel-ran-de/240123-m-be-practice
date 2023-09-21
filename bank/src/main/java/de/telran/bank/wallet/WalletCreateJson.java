package de.telran.bank.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class WalletCreateJson {
    @JsonProperty
    @NotNull
    private final UUID uuid;

    @JsonProperty
    @NotNull
    private final String currency;

    public WalletCreateJson(UUID uuid, String currency) {
        this.uuid = uuid;
        this.currency = currency;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getCurrency() {
        return currency;
    }
}
