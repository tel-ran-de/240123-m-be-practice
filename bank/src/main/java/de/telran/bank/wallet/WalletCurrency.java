package de.telran.bank.wallet;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CurrencyValidator.class)//says the logic of validation is in which class
@Target({ElementType.FIELD})
//decides what level this annotation would work like on only fileds/ class level or both fields and class level.
@Retention(RetentionPolicy.RUNTIME)
//decides up to which time this annotation should be valid like till compile time or runtime
public @interface WalletCurrency {

    String message() default "Invalid currency";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
