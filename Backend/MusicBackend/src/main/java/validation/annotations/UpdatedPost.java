package validation.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.*;

import validation.validators.UpdatedPostValidator;

@Constraint(validatedBy = UpdatedPostValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface UpdatedPost 
{
	String message() default "The post is not updated.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String idfield();
	String titlefield();
	String typefield();
	String textContentField();
	String searchField();
	String contentPathField();
}
