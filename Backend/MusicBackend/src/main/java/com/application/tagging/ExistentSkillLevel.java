package com.application.tagging;

public class ExistentSkillLevel 
{
	private String name;
	
	public ExistentSkillLevel()
	{
		name = "undefined";
	}
	
	public ExistentSkillLevel(String name)
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
