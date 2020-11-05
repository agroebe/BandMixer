package com.application.searching;

import javax.persistence.criteria.Subquery;
import javax.persistence.criteria.CriteriaQuery;

public abstract class MappingQuery<T>
{
	protected RootHandler<T> handler;
	
	protected MappingQuery(RootHandler<T> handler)
	{
		this.handler = handler;
	}
	
	public abstract CriteriaQuery<T> generate();
	
	public abstract Subquery<T> formulate();
}
