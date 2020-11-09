package com.application;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import validation.ValidationErrorResponse;
import validation.Violation;

/**
 * This Class is used by the application when the validators indicate constraint violations.
 * It ensures an object with useful error messages is returned, rather than a generic BadRequest.
 * @author Tim Schommer
 *
 */
@ControllerAdvice
public class ErrorHandlingControllerAdvice 
{
	/**
	 * Handles the case where the constraint violation is indicated by a
	 * ConstraintViolationException.
	 * 
	 * @param e
	 * 		The exception that caused the violation.
	 * @return
	 * 		An object containing a list of violations.
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e)
	{
		ValidationErrorResponse err = new ValidationErrorResponse();
		for(ConstraintViolation violation : e.getConstraintViolations())
		{
			err.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
		}
		return err;
	}
	
	/**
	 * Handles the case where the constraint violation is indicated by a
	 * MethodArgumentNotValidException.
	 * 
	 * @param e
	 * 		The exception that caused the violation.
	 * @return
	 * 		An object containing a list of violations.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e)
	{
		ValidationErrorResponse err = new ValidationErrorResponse();
		for(ObjectError objectError : e.getBindingResult().getAllErrors())
		{
			if(objectError instanceof FieldError)
			{
				FieldError fieldError = (FieldError)objectError;
				err.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
			}
			else
			{
				err.getViolations().add(new Violation(objectError.getObjectName(), objectError.getDefaultMessage()));
			}
			
		}
		return err;
	}
}
