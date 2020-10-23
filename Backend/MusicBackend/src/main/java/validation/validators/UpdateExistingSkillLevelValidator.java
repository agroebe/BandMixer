package validation.validators;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.skill_level.SkillLevel;
import com.application.skill_level.SkillLevelRepository;
import com.application.util.BeanUtil;

import validation.annotations.UpdateExistentSkillLevel;

public class UpdateExistingSkillLevelValidator implements ConstraintValidator<UpdateExistentSkillLevel, Object>
{
	@Autowired
	SkillLevelRepository repo;
	
	private String nameField;
	private String valueField;
	private String newNameField;
	private String msg;
	
	public void initialize(UpdateExistentSkillLevel constraint)
	{
		repo = BeanUtil.getBean(SkillLevelRepository.class);
		nameField = constraint.nameField();
		newNameField = constraint.newNameField();
		valueField = constraint.valueField();
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
		String name = (String)new BeanWrapperImpl(value).getPropertyValue(nameField);
		String newName = (String)new BeanWrapperImpl(value).getPropertyValue(newNameField);
		Integer valuefd = (Integer)new BeanWrapperImpl(value).getPropertyValue(valueField);
		if(name == null)
		{
			msg = "Skill level name was not set";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		if(name.equals("unset"))
		{
			msg = "Cannot update the value for 'unset'";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		Optional<SkillLevel> find = repo.findByName(name);
		if(!find.isPresent())
		{
			msg = "The skill level: " + name + " does not exist.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		SkillLevel found = find.get();
		if((newName == null || newName.equals("")) && valuefd == null)
		{
			msg = "Nothing given to update with";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		else if((newName == null || newName.equals("")))
		{
			if(valuefd == found.getValue())
			{
				msg = "Nothing given to update with";
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
				return false;
			}
			if(valuefd <= 0)
			{
				msg = "All skill level values must be non-negative, "
						+ "and only the 'unset' skill level may use the value 0";
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
				return false;
			}
		}
		else if(valuefd == null)
		{
			if(newName.contains(" ") || newName.contains("\t"))
			{
				msg = "Skill Level names cannot contain whitespace.";
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
				return false;
			}
			if(newName.equals("unset"))
			{
				msg = "Cannot change a skill level into 'unset'";
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
				return false;
			}
			if(newName.equals(name))
			{
				msg = "Nothing given to update with";
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
				return false;
			}
			Optional<SkillLevel> newFind = repo.findByName(newName);
			if(newFind.isPresent())
			{
				msg = "The new skill level name '" + newName + "' already exists.";
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
				return false;
			}
		}
		else
		{
			if(newName.equals(name) && valuefd == found.getValue())
			{
				msg = "Nothing given to update with";
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
				return false;
			}
			else if(newName.equals(name))
			{
				if(valuefd <= 0)
				{
					msg = "All skill level values must be non-negative, "
							+ "and only the 'unset' skill level may use the value 0";
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
					return false;
				}
			}
			else if(valuefd == found.getValue())
			{
				if(newName.contains(" ") || newName.contains("\t"))
				{
					msg = "Skill Level names cannot contain whitespace.";
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
					return false;
				}
				if(newName.equals("unset"))
				{
					msg = "Cannot change a skill level into 'unset'";
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
					return false;
				}
				Optional<SkillLevel> newFind = repo.findByName(newName);
				if(newFind.isPresent())
				{
					msg = "The new skill level name '" + newName + "' already exists.";
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
					return false;
				}
			}
			else
			{
				if(newName.contains(" ") || newName.contains("\t"))
				{
					msg = "Skill Level names cannot contain whitespace.";
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
					return false;
				}
				if(newName.equals("unset"))
				{
					msg = "Cannot change a skill level into 'unset'";
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
					return false;
				}
				Optional<SkillLevel> newFind = repo.findByName(newName);
				if(newFind.isPresent())
				{
					msg = "The new skill level name '" + newName + "' already exists.";
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
					return false;
				}
				if(valuefd <= 0)
				{
					msg = "All skill level values must be non-negative, "
							+ "and only the 'unset' skill level may use the value 0";
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
					return false;
				}
			}
		}
		return true;
	}
	
	String message()
	{
		return msg;
	}

}