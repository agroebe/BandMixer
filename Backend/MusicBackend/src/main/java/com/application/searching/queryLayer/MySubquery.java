package com.application.searching.queryLayer;

import javax.persistence.criteria.Subquery;

import com.application.searching.criteriaLayer.RootHandler;

public abstract class MySubquery<R> 
{
	protected QueryPart<R> child;
	protected RootHandler<R> handler;
	
	protected MySubquery(RootHandler<R> handler)
	{
		this.handler = handler;
	}
	
	
	
	public void setChild(QueryPart<R> c)
	{
		child = c;
	}
	
	public RootHandler<R> getHandler()
	{
		return handler;
	}
	
	public Subquery<R> formulate()
	{
		Subquery<R> cq =  (Subquery<R>) handler.getQuery();
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
}
