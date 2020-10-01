package com.application.tagging;

@ExistentTag
public class RequestExistentTag 
{
	private String name;
	
	public RequestExistentTag()
	{
		name = "undefined";
	}
	
	public RequestExistentTag(String name)
	{
		if(name == null || name.trim().equals(""))
		{
			this.name = "undefined";
		}
		else
		{
			this.name = name;
		}
	}
	
	public void setName(String name)
	{
		if(name == null || name.trim().equals(""))
		{
			this.name = "undefined";
		}
		else
		{
			this.name = name;
		}
	}
	
	public String getName()
	{
		return name;
	}
	
}
