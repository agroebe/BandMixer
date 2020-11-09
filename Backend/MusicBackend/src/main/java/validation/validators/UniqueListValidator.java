package validation.validators;

import java.util.HashSet;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.application.ProgramaticValidator;
import com.application.tagging.RequestTagApplication;

import validation.annotations.UniqueList;

/**
 * Class to validate a list of TagApplications. Uses ProgrammaticValidator to ensure that each entry is valid, 
 * and ensures that there are no duplicate Tags in the list.
 * @author Tim Schommer
 *
 */
public class UniqueListValidator implements ConstraintValidator<UniqueList, List<RequestTagApplication>>
{	
	@Autowired
	private ProgramaticValidator validator;
	
	public void initialize(UniqueList constraint)
	{
	}
	
	@Override
	public boolean isValid(List<RequestTagApplication> value, ConstraintValidatorContext context) {
		if(value == null)
		{
			String msg = "Cannot pass a null parameter.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		HashSet<String> tags = new HashSet<>();
		for(RequestTagApplication tag : value)
		{
			validator.validate(tag);
			String name = tag.getTag().getName();
			if(tags.contains(name))
			{
				String msg = "The tag '" + name + "' occurs more than once.";
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
				return false;
			}
			tags.add(name);
		}
		return true;
	}

}
