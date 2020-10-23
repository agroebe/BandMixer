package com.application.tagging;

import validation.annotations.NewTag;

@NewTag(namefield="name")
public class RequestNewTag 
{
	private String name;
	private boolean acceptsSkill;
	
	public RequestNewTag()
	{
		name = null;
		acceptsSkill = true;
	}
	
	public RequestNewTag(String name, boolean acceptsSkill)
	{
		this.name = (name == null?null:name.trim());
		this.acceptsSkill = acceptsSkill;
	}
	
	public void setName(String name)
	{
		this.name = (name == null?null:name.trim());
	}
	
	public String getName()
	{
		return name;
	}
	
	
	public void setAcceptsSkill(boolean acceptsSkill)
	{
		this.acceptsSkill = acceptsSkill;
	}
	
	public boolean getAcceptsSkill()
	{
		return acceptsSkill;
	}
}
