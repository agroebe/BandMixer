package validation.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import validation.validators.UpdateExistingSkillLevelValidator;

/**
 * Annotation indicating that a class should be considered to contain information for updating a SkillLevel
 * and should be validated accordingly.
 * @author Tim Schommer
 *
 */
@Constraint(validatedBy = UpdateExistingSkillLevelValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface UpdateExistentSkillLevel 
{
	String message() default "The skill level does not exist.";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	public String nameField();
	public String newNameField();
	public String valueField();
}
