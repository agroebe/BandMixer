package com.application.posts;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistentPostValidator implements ConstraintValidator<ExistentPost, Object>
{
	@Autowired
	PostRepository repo;
	
	
	public void initialize(ExistentPost constraint)
	{
		repo = BeanUtil.getBean(PostRepository.class);
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Long idValue = (Long)new BeanWrapperImpl(value).getPropertyValue("id");
		Optional<Post> find = repo.findById(idValue);
		if(!find.isPresent())
		{
			return false;
		}
		return true;
	}

}
