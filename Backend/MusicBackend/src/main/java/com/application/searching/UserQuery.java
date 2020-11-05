package com.application.searching;

import java.util.EnumSet;

import javax.persistence.criteria.CriteriaQuery;

import com.application.people.User;

public class UserQuery extends Query<BasicQueryPart> 
{
	public UserQuery()
	{
		super(QueryType.User);
	}
	
	
	public CriteriaQuery<User> generate(RootHandler handler) {
		return super.generate(handler, User.class);
	}

	@Override
	public EnumSet<QueryClass> getDependencies() {
		// TODO Auto-generated method stub
		return null;
	}

}
