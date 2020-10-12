package validation.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.*;

import validation.validators.ExistentTagValidator;

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
