package com.application.tagging;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.BeanUtil;

public class ExistentSkillLevelValidator implements ConstraintValidator<ExistentSkillLevel, Object>
{
	@Autowired
	SkillLevelRepository repo;
	
	private String nameField;
	private String msg;
	
	public void initialize(ExistentSkillLevel constraint)
	{
		repo = BeanUtil.getBean(SkillLevelRepository.class);
		nameField = constraint.nameField();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String name = (String)new BeanWrapperImpl(value).getPropertyValue(nameField);
		if(name == null)
		{
			msg = "Skill level name was not set";
			return false;
		}
		Optional<SkillLevel> find = repo.findByName(name);
		if(!find.isPresent())
		{
			msg = "The skill level: " + name + " does not exist.";
			return false;
		}
		return true;
	}
	
	String message()
	{
		return msg;
	}

}