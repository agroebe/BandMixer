package com.application.tagging;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.*;

@Constraint(validatedBy = NewTagValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface NewTag 
{
	String message() default "The tag does not exist.";
	String namefield();

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
