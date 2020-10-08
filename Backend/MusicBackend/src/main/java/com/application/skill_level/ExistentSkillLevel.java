package com.application.skill_level;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ExistentSkillLevelValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExistentSkillLevel {
	String message() default "The skill level does not exist.";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	public String nameField();
}
