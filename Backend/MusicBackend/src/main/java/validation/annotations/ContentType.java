package validation.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.*;

import validation.validators.ContentTypeValidator;

/**
 * Annotation indicating that a field is a String indicating a content type and the validity of said String should be checked.
 * @author Tim Schommer
 *
 */
@Constraint(validatedBy = ContentTypeValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface ContentType 
{
	String message() default "The content type does not exist.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
