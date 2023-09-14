package de.telran.bank.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class BadRequestBody {

    @JsonProperty
    private final Map<String, String> errors;

    @JsonCreator
    public BadRequestBody(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
