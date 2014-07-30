package main.java.co.uk.myhandicap.validation.annotation;

import main.java.co.uk.myhandicap.validation.validator.PasswordMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Password Match Annotation
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 30/07/2014
 * @project MyHandicapApp
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
@Documented
public @interface PasswordMatch {

    String message() default "{co.uk.myhandicap.validation.annotation.PasswordMatch}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String password();
    String confirmPassword();

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List
    {
        PasswordMatch[] value();
    }

}
