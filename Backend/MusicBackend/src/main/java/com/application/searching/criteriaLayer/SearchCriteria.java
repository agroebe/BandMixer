package com.application.searching.criteriaLayer;

import javax.persistence.criteria.Predicate;

public abstract class SearchCriteria<T>
{
	protected RootHandler<T> handler;
	
	protected SearchCriteria(RootHandler<T> handler)
	{
		this.handler = handler;
	}
	public abstract Predicate getPredicate();
}
