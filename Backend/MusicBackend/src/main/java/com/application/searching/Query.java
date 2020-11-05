package com.application.searching;

import java.util.EnumSet;

import javax.persistence.criteria.CriteriaQuery;

public abstract class Query<T,F extends QueryPart> extends MappingQuery<T>
{
	protected F child;
	
	protected Query(QueryType t)
	{
		super(t);
	}
	
	protected <T> CriteriaQuery<T> generate(RootHandler handler, Class<T> clazz)
	{
		CriteriaQuery<T> cq = handler.getQuery(clazz);
		if(child != null)
		{
			cq.select(handler.getRoot(clazz)).distinct(true).where(child.generate(handler).getPredicate(handler));
		}
		else
		{
			cq.select(handler.getRoot(clazz)).distinct(true);
		}
		return cq;
	}
	public abstract EnumSet<QueryClass> getDependencies();
	
	public void setChild(F c)
	{
		child = c;
	}
}
