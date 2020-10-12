package validation.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.*;

import validation.validators.MatchedTagValidator;

@Constraint(validatedBy = MatchedTagValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface MatchedTag 
{
	String message() default "The post already contains the tag.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String idfield();
	String tagfield();
	int version();
}
