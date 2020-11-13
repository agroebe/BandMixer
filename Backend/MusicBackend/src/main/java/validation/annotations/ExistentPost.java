package validation.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.*;

import validation.validators.ExistentPostValidator;

/**
 * Annotation indicating that a field is the id of a Post that already exists and should be validated as such.
 * @author Tim Schommer
 *
 */
@Constraint(validatedBy = ExistentPostValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExistentPost 
{
	String message() default "The post does not exist.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
