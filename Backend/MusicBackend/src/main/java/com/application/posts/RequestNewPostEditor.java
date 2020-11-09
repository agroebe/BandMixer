package com.application.posts;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

import com.application.ProgramaticValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class RequestNewPostEditor extends PropertyEditorSupport 
{
	private ObjectMapper objectMapper;
	private ProgramaticValidator validator;
	
	public RequestNewPostEditor(ObjectMapper objectMapper, ProgramaticValidator validator)
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
			RequestNewPost post = new RequestNewPost();
			try
			{
				post = objectMapper.readValue(text, RequestNewPost.class);
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
