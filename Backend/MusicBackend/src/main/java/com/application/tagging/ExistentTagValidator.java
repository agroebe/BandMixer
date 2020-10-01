package com.application.tagging;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.BeanUtil;

public class ExistentTagValidator implements ConstraintValidator<ExistentTag, Object>
{
	@Autowired
	TagRepository repo;
	
	private String name;
	
	public void initialize(ExistentTag constraint)
	{
		repo = BeanUtil.getBean(TagRepository.class);
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		name = (String)new BeanWrapperImpl(value).getPropertyValue("name");
		Optional<Tag> find = repo.findByName(name);
		if(!find.isPresent())
		{
			return false;
		}
		return true;
	}
	
	String message()
	{
		return "The tag: " + name + " does not exist.";
	}

}
