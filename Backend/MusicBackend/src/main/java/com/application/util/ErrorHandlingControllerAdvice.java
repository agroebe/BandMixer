package com.application.util;
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

@ControllerAdvice
public class ErrorHandlingControllerAdvice 
{
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e)
	{
		ValidationErrorResponse err = new ValidationErrorResponse();
		System.out.println("About to start loop1");
		System.out.println("size: " + e.getConstraintViolations().size());
		for(ConstraintViolation violation : e.getConstraintViolations())
		{
			err.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
			System.out.println("constraint violation: " + violation.getPropertyPath().toString() + " Message: " + violation.getMessage());
		}
		System.out.println("Done with loop 1");
		return err;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e)
	{
		ValidationErrorResponse err = new ValidationErrorResponse();
		System.out.println("About to start loop2");
		System.out.println("size: " + e.getBindingResult().getFieldErrorCount());
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
			
			System.out.println("param violation: " + objectError.getObjectName() + " Message: " + objectError.getCode());
		}
		System.out.println("Done with loop 2");
		return err;
	}
}
