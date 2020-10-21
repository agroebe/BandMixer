package com.application;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

@Service
public class ProgramaticValidator
{
	private Validator validator;
	
	public ProgramaticValidator(Validator v)
	{
		validator = v;
	}
	
	public <E> void validate(E value)
	{
		Set<ConstraintViolation<E>> violations = validator.validate(value);
		if(!violations.isEmpty())
		{
			throw new ConstraintViolationException(violations);
		}
	}
}
