package validation.validators;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.tagging.Tag;
import com.application.tagging.TagRepository;
import com.application.util.BeanUtil;

import validation.annotations.UpdateTag;

/**
 * Class to validate a data wrapper for updating a Tag. 
 * Ensures that Tag being updated exists, the new values are valid, and something is actually being changed.
 * @author Tim Schommer
 *
 */
public class UpdateTagValidator implements ConstraintValidator<UpdateTag, Object>
{
	@Autowired
	TagRepository repo;
	
	private String namefield;
	private String newNameField;
	private String allowSkillField;
	
	public void initialize(UpdateTag constraint)
	{
		repo = BeanUtil.getBean(TagRepository.class);
		namefield = constraint.namefield();
		newNameField = constraint.newNameField();
		allowSkillField = constraint.allowSkillField();
	}
	
	/**
	 * Ensures that Tag being updated exists, the new values are valid (not the name of an already existing Tag),
	 * and something is actually being changed.
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String name = (String)new BeanWrapperImpl(value).getPropertyValue(namefield);
		if(name == null || name.equals(""))
		{
			String msg = "The tag name was not set.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		Optional<Tag> find = repo.findByName(name);
		if(!find.isPresent())
		{
			String msg = "The tag: " + name + " does not exist.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		Tag found = find.get();
		String newName = (String)new BeanWrapperImpl(value).getPropertyValue(newNameField);
		boolean allowSkill = (boolean)new BeanWrapperImpl(value).getPropertyValue(allowSkillField);
		boolean noNameChange = (newName == null || newName.equals("") || newName.equals(name));
		boolean noAllowChange = allowSkill == found.getAllowskill();
		if(noNameChange && noAllowChange)
		{
			String msg = "Nothing given to update with.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		else if(!noNameChange)
		{
			if(newName.contains(" ") || newName.contains("\t"))
			{
				String msg = "Tag names cannot contain whitespace.";
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
				return false;
			}
			Optional<Tag> found2 = repo.findByName(newName);
			if(found2.isPresent())
			{
				String msg = "The new tag name '" + newName + "' already exists";
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
				return false;
			}
		}
		return true;
	}

}
