package com.application.tagging;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.util.BeanUtil;

public class ExistentTagValidator implements ConstraintValidator<ExistentTag, Object>
{
	@Autowired
	TagRepository repo;
	
	private String namefield;
	
	public void initialize(ExistentTag constraint)
	{
		repo = BeanUtil.getBean(TagRepository.class);
		namefield = constraint.namefield();
	}
	
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
		return true;
	}

}
