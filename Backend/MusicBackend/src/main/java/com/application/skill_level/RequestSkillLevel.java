package com.application.skill_level;

import validation.annotations.ExistentSkillLevel;

/**
 * Wrapper class on information needed to identify a SkillLevel that should already exist. Contains the name of the SkillLevel
 * @author Tim Schommer
 *
 */
@ExistentSkillLevel(nameField="name")
public class RequestSkillLevel 
{
	private String name;
	
	public RequestSkillLevel()
	{
		name = "unset";
	}
	
	public RequestSkillLevel(String name)
	{
		if(name == null || name.trim().equals(""))
		{
			this.name = "unset";
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
			this.name = "unset";
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
