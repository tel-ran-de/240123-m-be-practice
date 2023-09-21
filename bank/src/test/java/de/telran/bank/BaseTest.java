package de.telran.bank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/schema-cleanup.sql")
@Sql("/schema.sql")
public abstract class BaseTest {

    @Autowired
    protected ObjectMapper objectMapper;

    protected String writeJson(Object value) throws JsonProcessingException {
        return objectMapper.writeValueAsString(value);
    }

    protected  <T> T readJson(MvcResult mvcResult, Class<T> cls) throws IOException {
        return objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), cls);
    }

}
