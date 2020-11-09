package validation.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import validation.validators.NewSkillLevelValidator;

/**
 * Annotation indicating that a class contains information for the creation of a new SkillLevel and should be validated as such.
 * @author Tim Schommer
 *
 */
@Constraint(validatedBy = NewSkillLevelValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface NewSkillLevel 
{
	String message() default "The skill level already exists.";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	public String nameField();
	public String valueField();
}
