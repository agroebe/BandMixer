package com.application.posts;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.*;

@Constraint(validatedBy = ExistentPostValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExistentPost 
{
	String message() default "The post does not exist.";
	
}
