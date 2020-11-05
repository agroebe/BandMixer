package com.application.searching;

public abstract class QueryPart<T> 
{
	protected RootHandler<T> handler;
	
	protected QueryPart(RootHandler<T> handler)
	{
		this.handler = handler;
	}
	public abstract SearchCriteria<T> generate();
}
