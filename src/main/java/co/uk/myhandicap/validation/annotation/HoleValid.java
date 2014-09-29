package main.java.co.uk.myhandicap.validation.annotation;

import main.java.co.uk.myhandicap.validation.validator.HoleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Hole Validation.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 14/07/14
 * @project MyHandicapApp
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = HoleValidator.class)
@Documented
public @interface HoleValid {

    String message() default "{co.uk.myhandicap.validation.annotation.holevalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String holePar();
    String holeScore();
    String holeSSI();
    String holeYards();

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List
    {
        HoleValid[] value();
    }

}
