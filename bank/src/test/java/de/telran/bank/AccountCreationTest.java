package de.telran.bank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.bank.account.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountCreationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreatedAccount() throws Exception {
        // given
        Account account = new Account(new UUID(5, 5), "Anton", "Ermak");

        // when
        MvcResult createResult = mvc.perform(MockMvcRequestBuilders.post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeJson(account)))
                .andReturn();

        MvcResult receiveResult = mvc.perform(MockMvcRequestBuilders.get("/account")).andReturn();

        // then
        Assertions.assertEquals(200, createResult.getResponse().getStatus());
        Assertions.assertEquals(account, readJson(receiveResult, Account.class));
    }
    @Test
    void shouldNotOwerwriteAccount() throws Exception {
        Account account = new Account(new UUID(5, 5), "Anton", "Ermak");
        Account account2 = new Account(new UUID(5, 5), "Ivan", "Ivanov");
        MvcResult createResult = mvc.perform(MockMvcRequestBuilders.post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeJson(account)))
                .andReturn();
        MvcResult createResult2 = mvc.perform(MockMvcRequestBuilders.post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeJson(account2)))
                .andReturn();
        // then
        Assertions.assertEquals(200, createResult.getResponse().getStatus());
        Assertions.assertEquals(400, createResult2.getResponse().getStatus());
    }

    private String writeJson(Account account) throws JsonProcessingException {
        return objectMapper.writeValueAsString(account);
    }

    private <T> T readJson(MvcResult mvcResult, Class<T> cls) throws IOException {
        return objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), cls);
    }
}
