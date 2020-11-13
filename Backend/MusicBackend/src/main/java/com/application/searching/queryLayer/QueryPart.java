package com.application.searching.queryLayer;

import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.criteriaLayer.SearchCriteria;

public abstract class QueryPart<T> 
{
	protected RootHandler<T> handler;
	
	protected QueryPart(RootHandler<T> handler)
	{
		this.handler = handler;
	}
	public abstract SearchCriteria<T> generate();
	
	public RootHandler<T> getHandler(){return handler;}
}
