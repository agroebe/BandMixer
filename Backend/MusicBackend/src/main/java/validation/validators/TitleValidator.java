package validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import validation.annotations.ValidTitle;

public class TitleValidator implements ConstraintValidator<ValidTitle, String>
{	
	public void initialize(ValidTitle constraint)
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
			String msg = "Cannot use empty string for title.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		return true;
	}

}
