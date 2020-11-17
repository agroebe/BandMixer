package com.application.searching.criteriaLayer;

import javax.persistence.EntityManager;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;

public interface RootHandler<T> 
{
	public AbstractQuery<T> getQuery();
	
	public Path<T> getRoot();
	
	public From<?,?> getFrom(QueryClass cls);
	
	public CriteriaBuilder getBuilder();
	
	public EntityManager getManager();
}
