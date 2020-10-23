package validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import validation.annotations.ContentType;

public class ContentTypeValidator implements ConstraintValidator<ContentType, String>
{	
	public void initialize(ContentType constraint)
	{
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null)
		{
			return true;
		}
		if(value.equals(""))
		{
			String msg = "Cannot use empty string for content type.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		return true;
	}

}
