package com.application.searching.queryLayer;

import javax.persistence.criteria.CriteriaQuery;

import com.application.searching.criteriaLayer.RootHandler;
import javax.persistence.EntityGraph;

public abstract class Query<T>
{
	protected QueryPart<T> child;
	protected RootHandler<T> handler;
	
	protected Query(RootHandler<T> handler)
	{
		this.handler = handler;
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
	
	
	
	public void setChild(QueryPart<T> c)
	{
		child = c;
	}
	
	public RootHandler<T> getHandler()
	{
		return handler;
	}
	
	public abstract EntityGraph<T> getGraph();
}
