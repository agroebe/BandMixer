package com.application.searching.queryLayer;

import com.application.people.User;
import com.application.posts.Post;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.criteriaLayer.UserRootHandler;

public class UserSubquery extends MySubquery<User>
{
	public UserSubquery(UserRootHandler handler) {
		super(handler);
	}

}
