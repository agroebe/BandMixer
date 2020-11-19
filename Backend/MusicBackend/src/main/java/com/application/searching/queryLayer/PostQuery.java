package com.application.searching.queryLayer;


import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

import com.application.people.User;
import com.application.posts.Post;
import com.application.searching.criteriaLayer.RootHandler;

public class PostQuery extends Query<Post> 
{

	public PostQuery(RootHandler<Post> handler) {
		super(handler);
	}

	@Override
	public EntityGraph<Post> getGraph() {

		EntityManager m = handler.getManager();
		EntityGraph<Post> g= m.createEntityGraph(Post.class);
		g.addAttributeNodes("owner");
		return g;
	}
	
}
