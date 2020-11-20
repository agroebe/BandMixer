package com.application.searching.queryLayer;


import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

import com.application.people.User;
import com.application.searching.criteriaLayer.RootHandler;

public class UserQuery extends Query<User>
{
	public UserQuery(RootHandler<User> handler) {
		super(handler);
	}

	@Override
	public EntityGraph<User> getGraph() {
		EntityManager m = handler.getManager();
		EntityGraph<User> g= m.createEntityGraph(User.class);
		return g;
	}
	
}
