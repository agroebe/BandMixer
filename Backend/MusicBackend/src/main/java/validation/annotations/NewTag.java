package validation.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.*;

import validation.validators.NewTagValidator;

/**
 * Annotation indicating that a class contains information for creating a new Tag and should be validated as such
 * @author Tim Schommer
 *
 */
@Constraint(validatedBy = NewTagValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface NewTag 
{
	String message() default "The tag does not exist.";
	String namefield();

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
