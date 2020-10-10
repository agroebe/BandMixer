package com.application.tagging;

@UpdateTag(namefield="name", newNameField="newName", allowSkillField="allowsSkill")
public class RequestUpdateTag 
{
	private String name;
	private String newName;
	private boolean allowsSkill;
	public RequestUpdateTag()
	{
		name = null;
		newName = null;
		allowsSkill = true;
	}
	
	public RequestUpdateTag(String name)
	{
		this.name = (name == null ? null : name.trim());
		this.allowsSkill = true;
	}
	
	public RequestUpdateTag(String name, boolean allowsSkill)
	{
		this.name = (name == null ? null : name.trim());
		this.allowsSkill = allowsSkill;
	}
	
	public RequestUpdateTag(String name, String newName, boolean allowsSkill)
	{
		this.name = (name == null ? null : name.trim());
		this.newName = (newName == null ? null : newName.trim());
		this.allowsSkill = allowsSkill;
	}
	
	public void setName(String name)
	{
		this.name = (name == null ? null : name.trim());
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setAllowsSkill(boolean allowsSkill)
	{
		this.allowsSkill = allowsSkill;
	}
	
	public boolean getAllowsSkill()
	{
		return allowsSkill;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = (newName==null ? null : newName.trim());
	}
}
