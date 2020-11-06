package com.application.searching;

import com.application.people.User;

public class UserOperation extends QueryOperationPart<User>
{

	public UserOperation(RootHandler<User> handler, SearchOperation op, String field, Object val) {
		super(handler, QueryClass.eUser, op, field, val);
	}

}