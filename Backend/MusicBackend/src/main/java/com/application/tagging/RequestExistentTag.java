package com.application.tagging;

import validation.annotations.ExistentTag;

/**
 * Wrapper class on the information needed to indicate a Tag that already exists.
 * Contains the name of a tag.
 * @author Tim Schommer
 *
 */
@ExistentTag(namefield="name")
public class RequestExistentTag 
{
	private String name;
	
	/**
	 * Default constructor. Name is set to null.
	 */
	public RequestExistentTag()
	{
		name = null;
	}
	
	/**
	 * Constructor that accepts a name. If not null, name is first trimmed.
	 * @param name
	 */
	public RequestExistentTag(String name)
	{
		this.name = (name == null ? null : name.trim());
	}
	
	/**
	 * Sets the name field. If not null, name is first trimmed.
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = (name == null ? null : name.trim());
	}
	
	/**
	 * Getter for the name.
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
}
