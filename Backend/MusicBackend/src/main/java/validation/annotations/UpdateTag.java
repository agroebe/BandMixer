package validation.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.*;

import validation.validators.UpdateTagValidator;

@Constraint(validatedBy = UpdateTagValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface UpdateTag 
{
	String message() default "The tag does not exist.";
	String namefield();
	String newNameField();
	String allowSkillField();

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
