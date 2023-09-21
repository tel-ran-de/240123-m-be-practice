package de.telran.bank.wallet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran.bank.account.AccountJson;

import java.util.List;

public class WalletsJson {
    @JsonProperty
    private List<WalletJson> wallets;

    @JsonCreator
    public WalletsJson(List<WalletJson> wallets) {
        this.wallets = wallets;
    }

    public List<WalletJson> getWallets() {
        return wallets;
    }
}
