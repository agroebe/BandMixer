package com.application.tagging;

@NewSkillLevel(nameField="name", valueField="value")
public class RequestNewSkillLevel 
{
	private String name;
	private Integer value;
	
	public RequestNewSkillLevel() {}
	
	public RequestNewSkillLevel(String nm, Integer val)
	{
		name = nm;
		value = val;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
}
