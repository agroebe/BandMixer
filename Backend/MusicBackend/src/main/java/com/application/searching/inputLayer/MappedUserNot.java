package com.application.searching.inputLayer;

import javax.validation.constraints.NotNull;

import com.application.people.User;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.SearchOperator;
import com.application.searching.criteriaLayer.UserRootHandler;
import com.application.searching.queryLayer.QueryOperatorPart;
import com.application.searching.queryLayer.QueryPart;

public class MappedUserNot extends MappedUserPart
{
	@NotNull
	private MappedUserPart child;
	
	public MappedUserNot()
	{
	}
	
	public MappedUserPart getChild(){return child;}
	
	public void setChild(MappedUserPart child) 
	{
		this.child = child;
	}
	
	@Override
	public QueryPart<User> map(UserRootHandler handler, QueryService service) {
		QueryOperatorPart<User> ret= new QueryOperatorPart<>(handler,SearchOperator.NOT);
		ret.addChild(child.map(handler, service));
		return ret;
	}

}
