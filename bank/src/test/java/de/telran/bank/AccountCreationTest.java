package de.telran.bank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.bank.account.AccountJson;
import de.telran.bank.web.BadRequestBodyJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/schema-cleanup.sql")
@Sql("/schema.sql")
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
        AccountJson accountJson = new AccountJson(new UUID(5, 5), "Anton", "Ermak");

        // when
        MvcResult createResult = createAccount(accountJson);
        MvcResult receiveResult = getAccount(accountJson.getUuid());

        // then
        Assertions.assertEquals(200, createResult.getResponse().getStatus());
        Assertions.assertEquals(readJson(receiveResult, AccountJson.class), accountJson);
    }

    @Test
    void shouldDiscardInvalidAccount() throws Exception {
        // given
        AccountJson accountJson = new AccountJson(null, "", "");

        // when
        MvcResult createResult = createAccount(accountJson);
        BadRequestBodyJson badRequestBodyJson = objectMapper.readValue(createResult.getResponse().getContentAsString(),
                BadRequestBodyJson.class);

        // then
        Assertions.assertEquals(400, createResult.getResponse().getStatus());
        Assertions.assertEquals("must not be null", badRequestBodyJson.getErrors().get("uuid"));
        Assertions.assertEquals("must not be blank", badRequestBodyJson.getErrors().get("firstName"));
        Assertions.assertEquals("must not be blank", badRequestBodyJson.getErrors().get("lastName"));
    }

    @Test
    void shouldNotOverwriteAccount() throws Exception {
        // given
        UUID uuid = new UUID(5, 5);
        AccountJson accountJson = new AccountJson(uuid, "Anton", "Ermak");
        AccountJson accountJson2 = new AccountJson(uuid, "Ivan", "Ivanov");

        // when
        MvcResult firstResult = createAccount(accountJson);
        MvcResult secondResult = createAccount(accountJson2);

        // then
        Assertions.assertEquals(200, firstResult.getResponse().getStatus());
        Assertions.assertEquals(400, secondResult.getResponse().getStatus());
    }

    @Test
    void shouldUpdateAccountWithSecretKey() throws Exception {
        // given
        UUID uuid = new UUID(5, 5);
        AccountJson accountJson = new AccountJson(uuid, "Anton", "Ermak");
        AccountJson accountJson2 = new AccountJson(accountJson.getUuid(), "Anton", "Ivanov");

        // when
        MvcResult createResult = createAccount(accountJson);
        MvcResult updateResult = updateAccount(accountJson2);
        MvcResult receiveResult = getAccount(accountJson.getUuid());


        // then
        Assertions.assertEquals(200, createResult.getResponse().getStatus());
        Assertions.assertEquals(200, updateResult.getResponse().getStatus());
        Assertions.assertEquals(readJson(receiveResult, AccountJson.class), accountJson2);
    }

    private MvcResult createAccount(AccountJson accountJson) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeJson(accountJson)))
                .andReturn();
    }

    private MvcResult getAccount(UUID id) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get("/account/{id}", id)).andReturn();
    }

    private MvcResult updateAccount(AccountJson accountJson) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.put("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Secret-Key", secretKey)
                        .content(writeJson(accountJson)))
                .andReturn();
    }

    private String writeJson(AccountJson accountJson) throws JsonProcessingException {
        return objectMapper.writeValueAsString(accountJson);
    }

    private <T> T readJson(MvcResult mvcResult, Class<T> cls) throws IOException {
        return objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), cls);
    }
}
