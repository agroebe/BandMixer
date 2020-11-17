package com.application.searching.queryLayer;


import com.application.posts.Post;
import com.application.searching.criteriaLayer.RootHandler;

public class PostQuery extends Query<Post> 
{

	public PostQuery(RootHandler<Post> handler) {
		super(handler);
	}
	
}
