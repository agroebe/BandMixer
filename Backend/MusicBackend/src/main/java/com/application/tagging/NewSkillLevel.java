package com.application.tagging;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Constraint(validatedBy = NewSkillLevelValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface NewSkillLevel 
{
	String message() default "The skill level already exists.";
	
	public String nameField();
	public String valueField();
}
