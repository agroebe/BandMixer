package com.application.posts;

public class RequestTag 
{
	private String name;
	private String skillLevelName;
	
	public RequestTag()
	{
		name = null;
		skillLevelName = null;
	}
	
	public RequestTag(String name, String skillLevelName)
	{
		this.name = name;
		this.skillLevelName = skillLevelName;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setSkillLevelName(String skillLevelName)
	{
		this.skillLevelName = skillLevelName;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSkillLevelName()
	{
		return skillLevelName;
	}
}
