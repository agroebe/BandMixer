package com.application.searching;


import com.application.people.User;

public class UserQuery extends Query<User> 
{
	public UserQuery(RootHandler<User> handler) {
		super(handler);
	}

}
