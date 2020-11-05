package com.application.searching;

import javax.persistence.EntityManager;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Root;

public interface RootHandler<T> 
{
	public AbstractQuery<T> getQuery();
	
	public Root<T> getRoot();
	
	public From<?,?> getFrom(QueryClass cls);
	
	public CriteriaBuilder getBuilder();
	
	public EntityManager getManager();
}
