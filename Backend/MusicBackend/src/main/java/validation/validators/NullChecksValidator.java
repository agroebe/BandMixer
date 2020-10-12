package validation.validators;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.util.BeanUtil;

import validation.annotations.NullChecks;

public class NullChecksValidator implements ConstraintValidator<NullChecks, Object>
{
	
	
	private String[] fields;
	
	public void initialize(NullChecks constraint)
	{
		fields = constraint.fields();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value == null)
		{
			String msg = "Parameter checked was null.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		BeanWrapperImpl getter = new BeanWrapperImpl(value);
		for(String field : fields)
		{
			Object test = getter.getPropertyValue(field);
			if(test == null)
			{
				String msg = "Field '" + field + "' was null.";
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
				return false;
			}
		}
		return true;
	}

}
