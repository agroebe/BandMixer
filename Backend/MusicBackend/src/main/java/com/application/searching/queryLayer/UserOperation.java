package com.application.searching.queryLayer;

import com.application.people.User;
import com.application.searching.criteriaLayer.QueryClass;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.criteriaLayer.SearchOperation;

public class UserOperation extends QueryOperationPart<User>
{

	public UserOperation(RootHandler<User> handler, SearchOperation op, String field, Object val) {
		super(handler, QueryClass.eUser, op, field, val);
	}

}