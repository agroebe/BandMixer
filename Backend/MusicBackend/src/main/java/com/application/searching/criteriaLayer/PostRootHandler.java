package com.application.searching.criteriaLayer;

import javax.persistence.EntityManager;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.application.people.User;
import com.application.posts.Post;

public class PostRootHandler  implements RootHandler<Post>
{
	private AbstractQuery<Post> query;
	private EntityManager em;
	private CriteriaBuilder cb;
	private Root<Post> postRoot;
	
	public PostRootHandler(EntityManager e)
	{
		em = e;
		cb = em.getCriteriaBuilder();
		query = cb.createQuery(Post.class);
		postRoot = query.from(Post.class);
		
	}
	
	protected PostRootHandler(EntityManager e, Subquery<Post> q)
	{
		em = e;
		cb = em.getCriteriaBuilder();
		query = q;
		postRoot = query.from(Post.class);
		
	}
	
	public UserRootHandler generateSubquery()
	{
		return new UserRootHandler(em, query.subquery(User.class));
	}
	
	public ApplicationRootHandler generateApplicationQuery()
	{
		return new ApplicationRootHandler(em, query.subquery(Long.class));
	}
	
	public AbstractQuery<Post> getQuery(){return query;}
	
	public Root<Post> getRoot(){return postRoot;}
	
	public From<?,?> getFrom(QueryClass cls)
	{
		switch(cls)
		{
			case ePost:
			{
				return postRoot;
			}
			default:
			{
				throw new IllegalArgumentException("Invalid class for a post query.");
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
