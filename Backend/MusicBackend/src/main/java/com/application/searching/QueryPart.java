package com.application.searching;

public abstract class QueryPart 
{
	protected QueryType type;
	
	public QueryPart(Query parent)
	{
		type = parent.getType();
	}
	public abstract SearchCriteria generate(RootHandler handler);
}
