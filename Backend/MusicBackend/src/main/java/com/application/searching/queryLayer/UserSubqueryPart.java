package com.application.searching.queryLayer;

import com.application.people.User;
import com.application.posts.Post;
import com.application.searching.criteriaLayer.QueryClass;
import com.application.searching.criteriaLayer.RootHandler;

public class UserSubqueryPart extends SubqueryPart<Post, User> 
{


	public UserSubqueryPart(RootHandler<Post> handler) {
		super(handler, "owner", QueryClass.ePost);
	}


}
