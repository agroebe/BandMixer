package validation.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.*;

import validation.validators.ExistentTagValidator;

/**
 * Annotation indicating that a class contains information to identify at Tag that already exists and should be validated as such.
 * @author Tim Schommer
 *
 */
@Constraint(validatedBy = ExistentTagValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExistentTag 
{
	String message() default "The tag does not exist.";
	String namefield();

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
