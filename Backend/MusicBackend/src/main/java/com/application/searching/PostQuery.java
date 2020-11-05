package com.application.searching;

import java.util.EnumSet;

import javax.persistence.criteria.CriteriaQuery;

import com.application.posts.Post;

public class PostQuery extends Query<QueryPart> 
{
	public PostQuery()
	{
		super(QueryType.Post);
	}
	
	public CriteriaQuery<Post> generate(RootHandler handler) {
		return super.generate(handler, Post.class);
	}
	
	@Override
	public EnumSet<QueryClass> getDependencies() {
		// TODO Auto-generated method stub
		return null;
	}

}
