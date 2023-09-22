package de.telran.bank;

import de.telran.bank.account.AccountEntity;
import de.telran.bank.account.AccountJson;
import de.telran.bank.account.AccountRepository;
import de.telran.bank.wallet.WalletCreateJson;
import de.telran.bank.wallet.WalletEntity;
import de.telran.bank.wallet.WalletRepository;
import de.telran.bank.wallet.WalletsJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


public class WalletCreationTest extends BaseTest {

    @Autowired
    private MockMvc mvc;


    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Value("${secretKey}")
    private String secretKey;

    /*
    1. Добавление новых тестов в проект (Wallet).
    2. Добавление новых валидаторов в контроллеры, тесты.
    3. Postman.
 */
    @Test
    void shouldCreateValidWallet() throws Exception {
        // given
        AccountJson accountJson = new AccountJson(new UUID(5, 5), "Anton", "Ermak");
        WalletCreateJson walletCreateJson = new WalletCreateJson(new UUID(7, 7), "USD");

        // when
        MvcResult createAccountResult = createAccount(accountJson);
        MvcResult createWalletResult = createWallet(walletCreateJson, accountJson.getUuid());
        List<WalletEntity> createdWallets = walletRepository.findByAccountId(accountJson.getUuid());

        // then
        Assertions.assertEquals(200, createAccountResult.getResponse().getStatus());
        Assertions.assertEquals(200, createWalletResult.getResponse().getStatus());
        Assertions.assertEquals(1, createdWallets.size());
        Assertions.assertEquals(BigDecimal.ZERO, createdWallets.get(0).getBalance());
    }

    @Test
    void shouldReceiveWalletsByAccountId() throws Exception {
        //given
        UUID accountUUID = new UUID(5, 5);
        accountRepository.save(new AccountEntity(accountUUID, "Anton", "Ermak"));
        walletRepository.save(new WalletEntity(new UUID(3, 3), "USD",
                new UUID(5, 5), BigDecimal.valueOf(1000)));

        //when
        MvcResult allWalletsByAccountIdResult = mvc
                .perform(MockMvcRequestBuilders.get("/account/{accountId}/wallets", accountUUID))
                .andReturn();
        WalletsJson walletsJson = readJson(allWalletsByAccountIdResult, WalletsJson.class);

        //then
        Assertions.assertEquals(200, allWalletsByAccountIdResult.getResponse().getStatus());
        Assertions.assertEquals(1, walletsJson.getWallets().size());
        Assertions.assertEquals(BigDecimal.valueOf(1000), walletsJson.getWallets().get(0).getBalance());
    }

    @Test
    void shouldDiscardNotValidCurrencyCode() throws Exception {
        //given
        AccountJson accountJson = new AccountJson(new UUID(5, 5), "Anton", "Ermak");
        WalletCreateJson walletCreateJson = new WalletCreateJson(new UUID(7, 7), "notexist");
        // when
        createAccount(accountJson);
        MvcResult createWalletResult = createWallet(walletCreateJson, accountJson.getUuid());
        //then
        Assertions.assertEquals(400, createWalletResult.getResponse().getStatus());

    }

    private MvcResult createAccount(AccountJson accountJson) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeJson(accountJson)))
                .andReturn();
    }

    private MvcResult createWallet(WalletCreateJson walletCreateJson, UUID accountId) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post("/account/{accountId}/wallet", accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeJson(walletCreateJson)))
                .andReturn();
    }
}
