package com.application.searching;


import com.application.posts.Post;

public class PostQuery extends Query<Post> 
{

	protected PostQuery(RootHandler<Post> handler) {
		super(handler);
	}
	
}
