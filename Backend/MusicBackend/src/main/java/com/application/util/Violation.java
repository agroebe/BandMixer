package com.application.util;

public class Violation 
{
	private final String fieldName;
	private final String message;
	
	public Violation(String name, String message)
	{
		this.fieldName = name;
		this.message = message;
	}
	
	public String getField()
	{
		return fieldName;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public String toString()
	{
		return "Error@" + fieldName + ": " + message;
	}
}
