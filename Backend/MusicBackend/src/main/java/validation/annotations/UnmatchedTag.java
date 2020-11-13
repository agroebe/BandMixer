package validation.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.*;

import validation.validators.UnmatchedTagValidator;

/**
 * Annotation indicating that a class contains information about a Tag that should not be already applied to a particular Tag.
 * @author Tim Schommer
 *
 */
@Constraint(validatedBy = UnmatchedTagValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface UnmatchedTag 
{
	String message() default "The post already contains the tag.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String idfield();
	String tagfield();
}
