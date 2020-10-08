package com.application.tagging;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.BeanUtil;

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
		String name = (String)new BeanWrapperImpl(value).getPropertyValue(nameField);
		String newName = (String)new BeanWrapperImpl(value).getPropertyValue(newNameField);
		Integer valuefd = (Integer)new BeanWrapperImpl(value).getPropertyValue(valueField);
		if(name == null)
		{
			msg = "Skill level name was not set";
			return false;
		}
		if(name.equals("unset"))
		{
			msg = "Cannot update the value for 'unset'";
			return false;
		}
		Optional<SkillLevel> find = repo.findByName(name);
		if(!find.isPresent())
		{
			msg = "The skill level: " + name + " does not exist.";
			return false;
		}
		if((newName == null || newName.equals("") || newName.equals(name)) && valuefd == null)
		{
			msg = "Nothing given to update with";
			return false;
		}
		if(newName != null)
		{
			if(newName.contains(" "))
			{
				msg = "Skill level names cannot contain whitespace.";
				return false;
			}
			if(newName.equals("unset"))
			{
				msg = "Cannnot change another skill level into 'unset'.";
				return false;
			}
			Optional<SkillLevel> find2 = repo.findByName(newName);
			if(find2.isPresent())
			{
				msg = "Skill level matching new name already exists.";
				return false;
			}
		}
		if(valuefd != null && valuefd < 0)
		{
			msg = "The new value (" + valuefd + ") for the level is outside of acceptable bounds. All values must be non-negative";
			return false;
		}
		if(valuefd != null && valuefd == find.get().getValue() && 
				(newName == null || name.equals(newName)))
		{
			msg = "Nothing given to update with";
			return false;
		}
		
		return true;
	}
	
	String message()
	{
		return msg;
	}

}