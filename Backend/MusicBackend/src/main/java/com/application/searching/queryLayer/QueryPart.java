package com.application.searching.queryLayer;

import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.criteriaLayer.SearchCriteria;

public abstract class QueryPart<R> 
{
	protected RootHandler<R> handler;
	
	protected QueryPart(RootHandler<R> handler)
	{
		this.handler = handler;
	}
	public abstract SearchCriteria<R> generate();
	
	public RootHandler<R> getHandler(){return handler;}
}
