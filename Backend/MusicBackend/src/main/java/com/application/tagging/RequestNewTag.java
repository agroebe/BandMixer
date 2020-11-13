package com.application.tagging;

import validation.annotations.NewTag;

/**
 * Wrapper class on the information needed to create a new tag.
 * Contains the name of a tag and whether it will actually work with a SkillLevel.
 * @author Tim Schommer
 *
 */
@NewTag(namefield="name")
public class RequestNewTag 
{
	private String name;
	private boolean acceptsSkill;
	
	/**
	 * Default constructor. Name is null and it will work with a SkillLevel
	 */
	public RequestNewTag()
	{
		name = null;
		acceptsSkill = true;
	}
	
	/**
	 * Constructs RequestNewTag with given name and skill level acceptance.
	 * @param name
	 * @param acceptsSkill
	 */
	public RequestNewTag(String name, boolean acceptsSkill)
	{
		this.name = (name == null?null:name.trim());
		this.acceptsSkill = acceptsSkill;
	}
	
	/**
	 * Setter for the name.
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = (name == null?null:name.trim());
	}
	
	/**
	 * Getter for the name.
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Setter for whether or not it accepts a SkillLevel.
	 * @param acceptsSkill
	 */
	public void setAcceptsSkill(boolean acceptsSkill)
	{
		this.acceptsSkill = acceptsSkill;
	}
	
	/**
	 * Getter for whether or not it accepts a SkillLevel.
	 * @return
	 */
	public boolean getAcceptsSkill()
	{
		return acceptsSkill;
	}
}
