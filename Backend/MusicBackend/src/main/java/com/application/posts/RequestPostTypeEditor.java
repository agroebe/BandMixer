package com.application.posts;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

import com.application.ProgramaticValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestPostTypeEditor extends PropertyEditorSupport 
{
	private ObjectMapper objectMapper;
	
	private ProgramaticValidator validator;
	
	public RequestPostTypeEditor(ObjectMapper objectMapper, ProgramaticValidator validator)
	{
		this.objectMapper = objectMapper;
		this.validator = validator;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException
	{
		if(StringUtils.isEmpty(text))
		{
			setValue(null);
		}
		else
		{
			RequestPostType post = new RequestPostType();
			try
			{
				post = objectMapper.readValue(text, RequestPostType.class);
			}
			catch(JsonProcessingException e)
			{
				throw new IllegalArgumentException(e);
			}
			validator.validate(post);
			setValue(post);
		}
	}
}