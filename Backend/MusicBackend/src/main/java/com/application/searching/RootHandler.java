package com.application.searching;

import java.util.EnumSet;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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


public class RootHandler 
{
	private EntityManager em;
	private EntityType<User> User_;
	private EntityType<Post> Post_;
	private EntityType<AppliedSkillLevel> AppliedSkillLevel_;
	private QueryType fullType;
	private CriteriaBuilder cb;
	private CriteriaQuery<?> query;
	private Subquery<User> subquery;
	private Root<Post> postRoot;
	private Root<User> userRoot;
	private Join<User,Post> joinUserPost;
	private Join<Post,AppliedSkillLevel> joinUserAppliedSkill;
	private Join<AppliedSkillLevel,Tag> joinUserTag;
	private Join<AppliedSkillLevel,SkillLevel> joinUserSkill;
	private Join<Post,AppliedSkillLevel> joinPostAppliedSkill;
	private Join<AppliedSkillLevel,Tag> joinPostTag;
	private Join<AppliedSkillLevel,SkillLevel> joinPostSkill;
	
	public RootHandler(EntityManager e, QueryType type, EnumSet<QueryDependency> dependencies)
	{
		em = e;
		fullType = type;
		cb = em.getCriteriaBuilder();
		User_ = em.getMetamodel().entity(User.class);
		Post_ = em.getMetamodel().entity(Post.class);
		AppliedSkillLevel_ = em.getMetamodel().entity(AppliedSkillLevel.class);
		if(type == QueryType.User)
		{
			query = cb.createQuery(User.class);
			if(dependencies.contains(QueryDependency.UserRoot))
			{
				userRoot = query.from(User.class);
				if(dependencies.contains(QueryDependency.UserPost))
				{
					joinUserPost = userRoot.join(User_.getDeclaredSingularAttribute("posts",Post.class),JoinType.LEFT);
					if(dependencies.contains(QueryDependency.UserApplication))
					{
						joinUserAppliedSkill = joinUserPost.join(Post_.getDeclaredSingularAttribute("tags",AppliedSkillLevel.class),JoinType.LEFT);
						joinUserTag = joinUserAppliedSkill.join(AppliedSkillLevel_.getDeclaredSingularAttribute("tag",Tag.class),JoinType.LEFT);
						joinUserSkill = joinUserAppliedSkill.join(AppliedSkillLevel_.getDeclaredSingularAttribute("level",SkillLevel.class),JoinType.LEFT);
					}
				}
			}
		}
		else
		{
			query = cb.createQuery(Post.class);
			if(dependencies.contains(QueryDependency.PostRoot))
			{
				postRoot = query.from(Post.class);
				if(dependencies.contains(QueryDependency.PostApplication))
				{
					joinPostAppliedSkill = postRoot.join(Post_.getDeclaredSingularAttribute("tags",AppliedSkillLevel.class),JoinType.LEFT);
					joinPostTag = joinPostAppliedSkill.join(AppliedSkillLevel_.getDeclaredSingularAttribute("tag",Tag.class),JoinType.LEFT);
					joinPostSkill = joinPostAppliedSkill.join(AppliedSkillLevel_.getDeclaredSingularAttribute("level",SkillLevel.class),JoinType.LEFT);
				}
				if(dependencies.contains(QueryDependency.UserRoot))
				{
					subquery = query.subquery(User.class);
					userRoot = subquery.from(User.class);					
					if(dependencies.contains(QueryDependency.UserPost))
					{
						joinUserPost = userRoot.join(User_.getDeclaredSingularAttribute("posts",Post.class),JoinType.LEFT);
						if(dependencies.contains(QueryDependency.UserApplication))
						{
							joinUserAppliedSkill = joinUserPost.join(Post_.getDeclaredSingularAttribute("tags",AppliedSkillLevel.class),JoinType.LEFT);
							joinUserTag = joinUserAppliedSkill.join(AppliedSkillLevel_.getDeclaredSingularAttribute("tag",Tag.class),JoinType.LEFT);
							joinUserSkill = joinUserAppliedSkill.join(AppliedSkillLevel_.getDeclaredSingularAttribute("level",SkillLevel.class),JoinType.LEFT);
						}
					}
				}
			}
			
		}
	}
	
	public From getFrom(QueryType type, QueryClass cls)
	{
		From ret = null;
		if(fullType == type && fullType == QueryType.Post)
		{
			switch(cls)
			{
				case ePost:
				{
					ret = postRoot;
					break;
				}
				case eAppliedSkill:
				{
					ret = joinPostAppliedSkill;
					break;
				}
				case eTag:
				{
					ret = joinPostTag;
					break;
				}
				case eSkill:
				{
					ret = joinPostSkill;
					break;
				}
				default:
				{
					throw new IllegalArgumentException("Illegal query class for a post query.");
				}
			}
		}
		else
		{
			switch(cls)
			{
				case eUser:
				{
					ret = userRoot;
					break;
				}
				case ePost:
				{
					ret = joinUserPost;
					break;
				}
				case eAppliedSkill:
				{
					ret = joinUserAppliedSkill;
					break;
				}
				case eTag:
				{
					ret = joinUserTag;
					break;
				}
				case eSkill:
				{
					ret = joinUserSkill;
					break;
				}
			}
		}
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public <T> CriteriaQuery<T> getQuery(Class<T> clazz)
	{
		if(User.class == clazz && fullType != QueryType.User)
		{
			return (CriteriaQuery<T>) subquery;
		}
		return (CriteriaQuery<T>) query;
	}
	
	public CriteriaBuilder getBuilder()
	{
		return cb;
	}
	
	public EntityManager getManager()
	{
		return em;
	}
	
	@SuppressWarnings("unchecked")
	public <T> Root<T> getRoot(Class<T> clazz)
	{
		Root<T> ret = null;
		if(User.class == clazz)
		{
			ret = (Root<T>) userRoot;
		}
		else
		{
			ret = (Root<T>) postRoot;
		}
		return ret;
	}
}
