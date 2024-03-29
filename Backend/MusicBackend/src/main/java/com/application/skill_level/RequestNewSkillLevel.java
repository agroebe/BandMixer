package com.application.skill_level;

import validation.annotations.NewSkillLevel;

/**
 * Wrapper class on information needed to create a new SkillLevel. Contains name and value of new SkillLevel.
 * @author Tim Schommer
 *
 */
@NewSkillLevel(nameField="name", valueField="value")
public class RequestNewSkillLevel 
{
	private String name;
	private Integer value;
	
	public RequestNewSkillLevel() {}
	
	public RequestNewSkillLevel(String nm, Integer val)
	{
		name = (nm == null ? null : nm.trim());
		value = val;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = (name == null ? null : name.trim());
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
}
