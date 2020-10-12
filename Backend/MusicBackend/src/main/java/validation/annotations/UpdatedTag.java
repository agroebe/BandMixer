package validation.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.*;

import validation.validators.UpdatedTagValidator;

@Constraint(validatedBy = UpdatedTagValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface UpdatedTag 
{
	String message() default "The post does not exist.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String idfield();
	String tagfield();
}
