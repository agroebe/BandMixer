package com.application.tagging;

import validation.annotations.UpdateTag;

/**
 * Wrapper class on the information needed to update a Tag.
 * Contains information on which Tag to update and how it should be updated.
 * @author Tim Schommer
 *
 */
@UpdateTag(namefield="name", newNameField="newName", allowSkillField="allowsSkill")
public class RequestUpdateTag 
{
	private String name;
	private String newName;
	private boolean allowsSkill;
	
	/**
	 * Default constructor. Name and newName are null, allowsSkill is true.
	 */
	public RequestUpdateTag()
	{
		name = null;
		newName = null;
		allowsSkill = true;
	}
	
	/**
	 * Constructs a RequestUpdateTag with the given name.
	 * If not null, the given name is first trimmed. 
	 * AllowsSkill is true.
	 * @param name
	 */
	public RequestUpdateTag(String name)
	{
		this.name = (name == null ? null : name.trim());
		this.allowsSkill = true;
	}
	
	/**
	 * Constructs a RequestUpdateTag with the given name and value for allowsSkill.
	 * If not null, the given name is first trimmed.
	 * @param name
	 */
	public RequestUpdateTag(String name, boolean allowsSkill)
	{
		this.name = (name == null ? null : name.trim());
		this.allowsSkill = allowsSkill;
	}
	
	/**
	 * Constructs a RequestUpdateTag with the given name, newName, and value for allowsSkill.
	 * If not null, the given name and newName are first trimmed.
	 * @param name
	 */
	public RequestUpdateTag(String name, String newName, boolean allowsSkill)
	{
		this.name = (name == null ? null : name.trim());
		this.newName = (newName == null ? null : newName.trim());
		this.allowsSkill = allowsSkill;
	}
	
	/**
	 * Setter for the name of the tag to be updated.
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = (name == null ? null : name.trim());
	}
	
	/**
	 * Getter for the name of the tag to be updated.
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Setter for allowsSkill
	 * @param allowsSkill
	 */
	public void setAllowsSkill(boolean allowsSkill)
	{
		this.allowsSkill = allowsSkill;
	}
	
	/**
	 * Getter for allowsSkill
	 * @return
	 */
	public boolean getAllowsSkill()
	{
		return allowsSkill;
	}

	/**
	 * Getter for the new name.
	 * @return
	 */
	public String getNewName() {
		return newName;
	}

	/**
	 * Setter for the new name.
	 * @param newName
	 */
	public void setNewName(String newName) {
		this.newName = (newName==null ? null : newName.trim());
	}
}
