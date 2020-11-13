package validation.validators;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.tagging.Tag;
import com.application.tagging.TagRepository;
import com.application.util.BeanUtil;

import validation.annotations.NewTag;

/**
 * Validates a data wrapper for creating a new Tag. Ensures that the name is valid and that a Tag with that name 
 * does not already exist.
 * @author Tim Schommer
 *
 */
public class NewTagValidator implements ConstraintValidator<NewTag, Object>
{
	@Autowired
	TagRepository repo;
	
	private String namefield;
	
	public void initialize(NewTag constraint)
	{
		repo = BeanUtil.getBean(TagRepository.class);
		namefield = constraint.namefield();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value == null)
		{
			String msg = "Cannot pass a null parameter.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		String name = (String)new BeanWrapperImpl(value).getPropertyValue(namefield);
		if(name == null || name.equals(""))
		{
			String msg = "The tag name was not present.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		Optional<Tag> find = repo.findByName(name);
		if(find.isPresent())
		{
			String msg = "The tag: " + name + " already exists.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		if(name.contains(" ") || name.contains("\t"))
		{
			String msg = "Tag names may not contain whitespace";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		return true;
	}

}
