package com.application;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

/**
 * This Service performs validation on objects that can be validated 
 * to enable code to force validations at particular times.
 * @author Tim Schommer
 *
 */
@Service
public class ProgramaticValidator
{
	/**
	 * The validator to validate with.
	 */
	private Validator validator;
	
	/**
	 * Constructor for the programmatic validator
	 * @param v
	 * 		The Validator implementation to use.
	 */
	public ProgramaticValidator(Validator v)
	{
		validator = v;
	}
	
	/**
	 * Validates the given value. Throw a ConstraintViolationException if it is invalid.
	 * @param value
	 * 		The value to validate.
	 */
	public <E> void validate(E value)
	{
		Set<ConstraintViolation<E>> violations = validator.validate(value);
		if(!violations.isEmpty())
		{
			throw new ConstraintViolationException(violations);
		}
	}
}
