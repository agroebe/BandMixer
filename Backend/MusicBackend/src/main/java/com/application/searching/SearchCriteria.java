package com.application.searching;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.application.people.User;
import com.application.posts.Post;
import com.application.skill_level.AppliedSkillLevel;
import com.application.tagging.Tag;

public abstract class SearchCriteria 
{
	public abstract Predicate getPredicate(Root<User> user, CriteriaQuery<?> query, CriteriaBuilder builder);
	
	public abstract Predicate getPredicate2(Root<Post> post, CriteriaQuery<?> query, CriteriaBuilder builder);
}
