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

import com.application.posts.Post;
import com.application.skill_level.AppliedSkillLevel;
import com.application.skill_level.SkillLevel;
import com.application.tagging.Tag;

public class ApplicationRootHandler implements RootHandler<Long> 
{
	private AbstractQuery<Long> query;
	private EntityManager em;
	private EntityType<AppliedSkillLevel> AppliedSkillLevel_;
	private CriteriaBuilder cb;
	private Root<AppliedSkillLevel> applicationRoot;
	private Join<AppliedSkillLevel,Post> joinPostAppliedSkill;
	private Join<AppliedSkillLevel,Tag> joinPostTag;
	private Join<AppliedSkillLevel,SkillLevel> joinPostSkill;
	private Path<Long> frm;
	
	public ApplicationRootHandler(EntityManager e, Subquery<Long> q)
	{
		em = e;
		cb = em.getCriteriaBuilder();
		AppliedSkillLevel_ = em.getMetamodel().entity(AppliedSkillLevel.class);
		query = q;
		applicationRoot = query.from(AppliedSkillLevel.class);
		joinPostAppliedSkill = applicationRoot.join(AppliedSkillLevel_.getDeclaredSingularAttribute("post",Post.class),JoinType.LEFT);
		joinPostTag = applicationRoot.join(AppliedSkillLevel_.getDeclaredSingularAttribute("tag",Tag.class),JoinType.LEFT);
		joinPostSkill = applicationRoot.join(AppliedSkillLevel_.getDeclaredSingularAttribute("level",SkillLevel.class),JoinType.LEFT);
		frm = joinPostAppliedSkill.get("id");
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
			case eAppliedSkill:
			{
				return applicationRoot;
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
				throw new IllegalArgumentException("Invalid class for a application query.");
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
