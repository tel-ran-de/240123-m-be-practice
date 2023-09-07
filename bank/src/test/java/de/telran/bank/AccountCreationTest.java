package de.telran.bank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.bank.account.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AccountCreationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${secretKey}")
    private String secretKey;

    @Test
    void shouldCreateAccount() throws Exception {
        // given
        Account account = new Account(new UUID(5, 5), "Anton", "Ermak");

        // when
        MvcResult createResult = createAccount(account);
        MvcResult receiveResult = getAccount(account.getUuid());

        // then
        Assertions.assertEquals(200, createResult.getResponse().getStatus());
        Assertions.assertEquals(readJson(receiveResult, Account.class), account);
    }

    @Test
    void shouldNotOverwriteAccount() throws Exception {
        // given
        UUID uuid = new UUID(5, 5);
        Account account = new Account(uuid, "Anton", "Ermak");
        Account account2 = new Account(uuid, "Ivan", "Ivanov");

        // when
        MvcResult firstResult = createAccount(account);
        MvcResult secondResult = createAccount(account2);

        // then
        Assertions.assertEquals(200, firstResult.getResponse().getStatus());
        Assertions.assertEquals(400, secondResult.getResponse().getStatus());
    }

    @Test
    void shouldUpdateAccountWithSecretKey() throws Exception {
        // given
        UUID uuid = new UUID(5, 5);
        Account account = new Account(uuid, "Anton", "Ermak");
        Account account2 = new Account(account.getUuid(), "Anton", "Ivanov");

        // when
        MvcResult createResult = createAccount(account);
        MvcResult updateResult = updateAccount(account2);
        MvcResult receiveResult = getAccount(account.getUuid());


        // then
        Assertions.assertEquals(200, createResult.getResponse().getStatus());
        Assertions.assertEquals(200, updateResult.getResponse().getStatus());
        Assertions.assertEquals(readJson(receiveResult, Account.class), account2);
    }

    private MvcResult createAccount(Account account) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeJson(account)))
                .andReturn();
    }

    private MvcResult getAccount(UUID id) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get("/account/{id}", id)).andReturn();
    }

    private MvcResult updateAccount(Account account) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.put("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Secret-Key", secretKey)
                        .content(writeJson(account)))
                .andReturn();
    }

    private String writeJson(Account account) throws JsonProcessingException {
        return objectMapper.writeValueAsString(account);
    }

    private <T> T readJson(MvcResult mvcResult, Class<T> cls) throws IOException {
        return objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), cls);
    }
}
