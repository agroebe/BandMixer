package com.application.tagging;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.util.BeanUtil;

public class NewSkillLevelValidator implements ConstraintValidator<NewSkillLevel, Object>
{
	@Autowired
	SkillLevelRepository repo;
	
	private String nameField;
	private String valueField;
	private String msg;
	
	public void initialize(NewSkillLevel constraint)
	{
		repo = BeanUtil.getBean(SkillLevelRepository.class);
		nameField = constraint.nameField();
		valueField = constraint.valueField();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String name = (String)new BeanWrapperImpl(value).getPropertyValue(nameField);
		Integer valuefd = (Integer)new BeanWrapperImpl(value).getPropertyValue(valueField);
		if(name == null)
		{
			msg = "Skill level name was not set";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		Optional<SkillLevel> find = repo.findByName(name);
		if(find.isPresent())
		{
			msg = "The skill level: " + name + " already exists.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		if(name.equals("") || name.contains(" "))
		{
			msg = "Skill level names cannot contain whitespace or be empty strings";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		if(valuefd == null)
		{
			msg = "The value for new skill level '" + name + "' was not set";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		if(valuefd < 0)
		{
			msg = "The value (" + valuefd + ") for new skill level '" + name + "' is outside of acceptable bounds. All values must be non-negative";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		if(valuefd == 0 && !name.equals("unset"))
		{
			msg = "Only the 'unset' skill level may carry the value of 0, all other skill levels must contain a greater value.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		if(name.equals("unset") && valuefd != 0)
		{
			msg = "The skill level 'unset' can only have a value of 0";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		return true;
	}
	
	String message()
	{
		return msg;
	}

}
