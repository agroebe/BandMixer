package com.application.searching.inputLayer;

import javax.validation.constraints.NotNull;

import com.application.people.User;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.UserRootHandler;
import com.application.searching.queryLayer.UserQuery;

public class MappedUserQuery extends MappedQuery<User>
{
	@NotNull
	private MappedUserPart child;
	
	public MappedUserQuery() {}
	
	public MappedUserPart getChild() {return child;}
	
	public void setChild(MappedUserPart child) {this.child = child;}
	

	@Override
	public UserQuery firstmap(QueryService service) {
		UserRootHandler h = (UserRootHandler)service.getUserHandler();
		UserQuery q = new UserQuery(h);
		q.setChild(this.child.map(h, service));
		return q;
	}
}
