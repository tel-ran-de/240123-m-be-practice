package de.telran.bank.wallet;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.util.Set;

public class CurrencyValidator implements ConstraintValidator<WalletCurrency, String> {

    private static final Set<String> acceptedValues = Set.of("USD", "EUR");

    @Override
    public void initialize(WalletCurrency constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return acceptedValues.contains(value);
    }
}
