package de.telran.bank.account;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Account is duplicated")
public class DuplicatedEntityException extends Exception {
    public DuplicatedEntityException() {
    }
}
