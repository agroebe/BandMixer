package com.application.skill_level;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

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
