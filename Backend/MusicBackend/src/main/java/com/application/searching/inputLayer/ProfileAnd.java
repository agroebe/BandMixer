package com.application.searching.inputLayer;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.SearchOperator;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.queryLayer.QueryOperatorPart;
import com.application.searching.queryLayer.QueryPart;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("and")
public class ProfileAnd extends MappedProfilePart 
{
	@NotEmpty
	private List<MappedProfilePart> children;
	
	public ProfileAnd()
	{
		children = new ArrayList<>();
	}
	
	public List<MappedProfilePart> getChildren(){return children;}
	
	public void setChildren(List<MappedProfilePart> children) 
	{
		this.children = (children == null ? new ArrayList<>() : children);
	}
	
	@Override
	public QueryPart<Long> map(SubPostRootHandler handler, QueryService service) {
		QueryOperatorPart<Long> ret= new QueryOperatorPart<>(handler,SearchOperator.AND);
		for(MappedProfilePart p : children)
		{
			ret.addChild(p.map(handler, service));
		}
		return ret;
	}

}
