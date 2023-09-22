package de.telran.bank.wallet;

import de.telran.bank.account.DuplicatedEntityException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class WalletController {

    private static final Logger LOG = LoggerFactory.getLogger(WalletController.class);

    @Autowired
    private WalletManagementService walletManagementService;

    @PostMapping(value = "/account/{accountId}/wallet", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void createWallet(@Valid @RequestBody WalletCreateJson walletCreateJson, @PathVariable UUID accountId) throws DuplicatedEntityException {
        LOG.info("Received wallet = {}", walletCreateJson);
       walletManagementService.save(new WalletEntity(walletCreateJson.getUuid(),
                walletCreateJson.getCurrency(),
                accountId, BigDecimal.ZERO));
    }


    @GetMapping("/account/{accountId}/wallets")
    public WalletsJson getWallets(@PathVariable UUID accountId) {
        List<WalletEntity> wallets = walletManagementService.getWallets(accountId);

        return new WalletsJson(wallets.stream().map(walletEntity -> new WalletJson(
                walletEntity.getId(), walletEntity.getCurrencyCode(), walletEntity.getAccountId(),
                walletEntity.getBalance())).collect(Collectors.toList()));
    }
}
