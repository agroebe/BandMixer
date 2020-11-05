package com.application.searching;

import javax.persistence.EntityManager;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import com.application.people.User;
import com.application.posts.Post;
import com.application.skill_level.AppliedSkillLevel;
import com.application.skill_level.SkillLevel;
import com.application.tagging.Tag;

public class PostRootHandler  implements RootHandler<Post>
{
	private AbstractQuery<Post> query;
	private EntityManager em;
	private EntityType<Post> Post_;
	private EntityType<AppliedSkillLevel> AppliedSkillLevel_;
	private CriteriaBuilder cb;
	private Root<Post> postRoot;
	private Join<Post,AppliedSkillLevel> joinPostAppliedSkill;
	private Join<AppliedSkillLevel,Tag> joinPostTag;
	private Join<AppliedSkillLevel,SkillLevel> joinPostSkill;
	
	public PostRootHandler(EntityManager e)
	{
		em = e;
		cb = em.getCriteriaBuilder();
		Post_ = em.getMetamodel().entity(Post.class);
		AppliedSkillLevel_ = em.getMetamodel().entity(AppliedSkillLevel.class);
		query = cb.createQuery(Post.class);
		postRoot = query.from(Post.class);
		joinPostAppliedSkill = postRoot.join(Post_.getDeclaredSingularAttribute("tags",AppliedSkillLevel.class),JoinType.LEFT);
		joinPostTag = joinPostAppliedSkill.join(AppliedSkillLevel_.getDeclaredSingularAttribute("tag",Tag.class),JoinType.LEFT);
		joinPostSkill = joinPostAppliedSkill.join(AppliedSkillLevel_.getDeclaredSingularAttribute("level",SkillLevel.class),JoinType.LEFT);
		
	}
	
	public UserRootHandler generateSubquery()
	{
		return new UserRootHandler(em, query.subquery(User.class));
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
			case eAppliedSkill:
			{
				return joinPostAppliedSkill;
			}
			case eTag:
			{
				return joinPostTag;
			}
			case eSkill:
			{
				return joinPostSkill;
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
