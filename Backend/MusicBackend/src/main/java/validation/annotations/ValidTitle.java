package validation.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.*;

import validation.validators.TitleValidator;

@Constraint(validatedBy = TitleValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidTitle 
{
	String message() default "The title is invalid.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
