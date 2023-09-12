package de.telran.bank.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

public class AccountJson {

    @JsonProperty
    private final UUID uuid;

    @JsonProperty
    private final String firstName;

    @JsonProperty
    private final String lastName;

    @JsonCreator
    public AccountJson(UUID uuid, String firstName, String lastName) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountJson accountJson = (AccountJson) o;
        return Objects.equals(uuid, accountJson.uuid) && Objects.equals(firstName, accountJson.firstName) && Objects.equals(lastName, accountJson.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Account{" +
                "uuid=" + uuid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
