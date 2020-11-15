package com.application.searching.inputLayer;

import com.application.people.User;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.UserRootHandler;
import com.application.searching.queryLayer.QueryPart;

public abstract class MappedUserPart 
{
	public abstract QueryPart<User> map(UserRootHandler handler, QueryService service);
}
