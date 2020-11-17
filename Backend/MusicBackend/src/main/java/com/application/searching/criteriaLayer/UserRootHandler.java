package com.application.searching.criteriaLayer;

import javax.persistence.EntityManager;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.application.people.User;

public class UserRootHandler implements RootHandler<User>
{
	private AbstractQuery<User> query;
	private EntityManager em;
	private CriteriaBuilder cb;
	private Root<User> userRoot;
	
	public UserRootHandler(EntityManager e)
	{
		em = e;
		cb = em.getCriteriaBuilder();
		query = cb.createQuery(User.class);
		userRoot = query.from(User.class);
	}
	
	public SubPostRootHandler generatePostQuery()
	{
		return new SubPostRootHandler(em, query.subquery(Long.class));
	}
	
	public UserRootHandler(EntityManager e, Subquery<User> q)
	{
		em = e;
		cb = em.getCriteriaBuilder();
		query = q;
		userRoot = query.from(User.class);
	}
	
	public AbstractQuery<User> getQuery(){return query;}
	
	public Root<User> getRoot(){return userRoot;}
	
	public From<?,?> getFrom(QueryClass cls)
	{
		switch(cls)
		{
			case eUser:
			{
				return userRoot;
			}
			default:
			{
				throw new IllegalArgumentException("Invalid class for a user query.");
			}
		}
	}
	
	public CriteriaBuilder getBuilder()
	{
		return cb;
	}
	
	public EntityManager getManager()
	{
		return em;
	}
}
