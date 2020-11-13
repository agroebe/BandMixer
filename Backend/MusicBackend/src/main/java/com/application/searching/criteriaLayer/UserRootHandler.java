package com.application.searching.criteriaLayer;

import javax.persistence.EntityManager;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.EntityType;

import com.application.people.User;
import com.application.posts.Post;
import com.application.skill_level.AppliedSkillLevel;
import com.application.skill_level.SkillLevel;
import com.application.tagging.Tag;

public class UserRootHandler implements RootHandler<User>
{
	private AbstractQuery<User> query;
	private EntityManager em;
	private EntityType<User> User_;
	private EntityType<Post> Post_;
	private EntityType<AppliedSkillLevel> AppliedSkillLevel_;
	private CriteriaBuilder cb;
	private Root<User> userRoot;
	private Join<User,Post> joinUserPost;
	private Join<Post,AppliedSkillLevel> joinUserAppliedSkill;
	private Join<AppliedSkillLevel,Tag> joinUserTag;
	private Join<AppliedSkillLevel,SkillLevel> joinUserSkill;
	
	public UserRootHandler(EntityManager e)
	{
		em = e;
		cb = em.getCriteriaBuilder();
		User_ = em.getMetamodel().entity(User.class);
		Post_ = em.getMetamodel().entity(Post.class);
		AppliedSkillLevel_ = em.getMetamodel().entity(AppliedSkillLevel.class);
		query = cb.createQuery(User.class);
		userRoot = query.from(User.class);
		joinUserPost = userRoot.join(User_.getDeclaredSingularAttribute("posts",Post.class),JoinType.LEFT);
		joinUserAppliedSkill = joinUserPost.join(Post_.getDeclaredSingularAttribute("tags",AppliedSkillLevel.class),JoinType.LEFT);
		joinUserTag = joinUserAppliedSkill.join(AppliedSkillLevel_.getDeclaredSingularAttribute("tag",Tag.class),JoinType.LEFT);
		joinUserSkill = joinUserAppliedSkill.join(AppliedSkillLevel_.getDeclaredSingularAttribute("level",SkillLevel.class),JoinType.LEFT);
	}
	
	public UserRootHandler(EntityManager e, Subquery<User> q)
	{
		em = e;
		cb = em.getCriteriaBuilder();
		User_ = em.getMetamodel().entity(User.class);
		Post_ = em.getMetamodel().entity(Post.class);
		AppliedSkillLevel_ = em.getMetamodel().entity(AppliedSkillLevel.class);
		query = q;
		userRoot = query.from(User.class);
		joinUserPost = userRoot.join(User_.getDeclaredSingularAttribute("posts",Post.class),JoinType.LEFT);
		joinUserAppliedSkill = joinUserPost.join(Post_.getDeclaredSingularAttribute("tags",AppliedSkillLevel.class),JoinType.LEFT);
		joinUserTag = joinUserAppliedSkill.join(AppliedSkillLevel_.getDeclaredSingularAttribute("tag",Tag.class),JoinType.LEFT);
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
			case ePost:
			{
				return joinUserPost;
			}
			case eAppliedSkill:
			{
				return joinUserAppliedSkill;
			}
			case eTag:
			{
				return joinUserTag;
			}
			case eSkill:
			{
				return joinUserSkill;
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
