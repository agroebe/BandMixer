package com.application.searching;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Subquery;

public abstract class Query<T> extends MappingQuery<T>
{
	protected QueryPart<T> child;
	
	protected Query(RootHandler<T> handler)
	{
		super(handler);
	}
	
	public CriteriaQuery<T> generate()
	{
		CriteriaQuery<T> cq = (CriteriaQuery<T>) handler.getQuery();
		if(child != null)
		{
			cq.select(handler.getRoot()).distinct(true).where(child.generate().getPredicate());
		}
		else
		{
			cq.select(handler.getRoot()).distinct(true);
		}
		return cq;
	}
	
	public Subquery<T> formulate()
	{
		Subquery<T> cq =  (Subquery<T>) handler.getQuery();
		if(child != null)
		{
			cq.select(handler.getRoot()).distinct(true).where(child.generate().getPredicate());
		}
		else
		{
			cq.select(handler.getRoot()).distinct(true);
		}
		return cq;
	}
	
	public void setChild(QueryPart<T> c)
	{
		child = c;
	}
}
