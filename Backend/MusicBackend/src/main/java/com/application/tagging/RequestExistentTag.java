package com.application.tagging;

import validation.annotations.ExistentTag;

@ExistentTag(namefield="name")
public class RequestExistentTag 
{
	private String name;
	
	public RequestExistentTag()
	{
		name = null;
	}
	
	public RequestExistentTag(String name)
	{
		this.name = (name == null ? null : name.trim());
	}
	
	public void setName(String name)
	{
		this.name = (name == null ? null : name.trim());
	}
	
	public String getName()
	{
		return name;
	}
	
}
