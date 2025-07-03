package com.hs.tours360.validations.auth;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AuthRegisterValidator.class) // Clase que hace la lógica
@Target({ ElementType.TYPE }) // Se aplica a clases, no a campos
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRegistroAuth {

    String message() default "El registro no es válido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
