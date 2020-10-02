package com.application.tagging;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Constraint(validatedBy = UpdateExistingSkillLevelValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface UpdateExistentSkillLevel 
{
	String message() default "The skill level does not exist.";
	
	public String nameField();
	public String newNameField();
	public String valueField();
}
