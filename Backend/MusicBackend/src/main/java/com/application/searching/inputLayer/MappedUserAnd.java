package com.application.searching.inputLayer;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.application.people.User;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.SearchOperator;
import com.application.searching.criteriaLayer.UserRootHandler;
import com.application.searching.queryLayer.QueryOperatorPart;
import com.application.searching.queryLayer.QueryPart;

public class MappedUserAnd extends MappedUserPart
{

	@NotEmpty
	private List<MappedUserPart> children;
	
	public MappedUserAnd()
	{
		children = new ArrayList<>();
	}
	
	public List<MappedUserPart> getChildren(){return children;}
	
	public void setChildren(List<MappedUserPart> children) 
	{
		this.children = (children == null ? new ArrayList<>() : children);
	}
	
	@Override
	public QueryPart<User> map(UserRootHandler handler, QueryService service) {
		QueryOperatorPart<User> ret= new QueryOperatorPart<>(handler,SearchOperator.AND);
		for(MappedUserPart p : children)
		{
			ret.addChild(p.map(handler, service));
		}
		return ret;
	}

}
