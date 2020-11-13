package validation.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.*;

import validation.validators.MatchedTagValidator;

/**
 * Annotation indicating that a class contains information about a Tag that should already be applied to an indicated Post
 * and should be validated as such. Version is used to indicate whether the tagfield should be expected to correspond to
 * a RequestExistentTag (version==1) or a RequestTagApplication (version==2).
 * @author Tim Schommer
 *
 */
@Constraint(validatedBy = MatchedTagValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface MatchedTag 
{
	String message() default "The post does not contain the tag.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String idfield();
	String tagfield();
	int version();
}
