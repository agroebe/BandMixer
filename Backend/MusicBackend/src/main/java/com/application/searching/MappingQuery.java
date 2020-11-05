package com.application.searching;

import javax.persistence.criteria.CriteriaQuery;

public abstract class MappingQuery<T>
{
	protected QueryType type;
	
	protected MappingQuery(QueryType t)
	{
		type = t;
	}
	
	public QueryType getType() {return type;}
	
	protected abstract CriteriaQuery<T> generate(RootHandler handler);
}
