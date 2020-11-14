package com.application.searching.queryLayer;

import com.application.people.User;
import com.application.posts.Post;
import com.application.searching.criteriaLayer.QueryClass;
import com.application.searching.criteriaLayer.RootHandler;

public class PostSubqueryPart extends SubqueryPart<User,Long>
{

	public PostSubqueryPart(RootHandler<User> handler) {
		super(handler, "id", QueryClass.eUser);
	}

}
