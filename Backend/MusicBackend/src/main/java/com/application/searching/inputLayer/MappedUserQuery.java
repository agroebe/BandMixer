package com.application.searching.inputLayer;

import com.application.people.User;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.queryLayer.Query;

public class MappedUserQuery implements MappedQuery<User> {

	@Override
	public Query<User> map(RootHandler<User> handler, QueryService service) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query<User> firstmap(QueryService service) {
		return map(service.getUserHandler(), service);
	}

}
