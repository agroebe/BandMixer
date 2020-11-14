package com.application.searching.criteriaLayer;

import javax.persistence.EntityManager;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.EntityType;

import com.application.people.User;
import com.application.posts.Post;
import com.application.skill_level.AppliedSkillLevel;
import com.application.skill_level.SkillLevel;
import com.application.tagging.Tag;

public class SubPostRootHandler implements RootHandler<Long>
{
	private AbstractQuery<Long> query;
	private EntityManager em;
	private CriteriaBuilder cb;
	private EntityType<Post> Post_;
	private Root<Post> postRoot;
	private Join<Post, User> joinUser;
	private Path<Long> frm;
	
	public SubPostRootHandler(EntityManager e, Subquery<Long> q) {
		em = e;
		cb = em.getCriteriaBuilder();
		Post_ = em.getMetamodel().entity(Post.class);
		query = q;
		postRoot = query.from(Post.class);
		joinUser = postRoot.join(Post_.getDeclaredSingularAttribute("owner",User.class),JoinType.LEFT);
		frm = joinUser.get("id");
	}
	
	public ApplicationRootHandler generateApplicationQuery()
	{
		return new ApplicationRootHandler(em, query.subquery(Long.class));
	}
	
	@Override
	public AbstractQuery<Long> getQuery() {
		return query;
	}

	@Override
	public Path<Long> getRoot() {
		return frm;
	}

	@Override
	public From<?, ?> getFrom(QueryClass cls) {
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

	@Override
	public CriteriaBuilder getBuilder() {
		return cb;
	}

	@Override
	public EntityManager getManager() {
		return em;
	}
}
