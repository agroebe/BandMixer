package validation.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import validation.validators.NullChecksValidator;

@Constraint(validatedBy = NullChecksValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface NullChecks 
{
	String message() default "The skill level already exists.";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	public String[] fields();
}
