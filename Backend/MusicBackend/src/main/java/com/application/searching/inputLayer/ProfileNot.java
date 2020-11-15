package com.application.searching.inputLayer;

import javax.validation.constraints.NotNull;

import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.SearchOperator;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.queryLayer.QueryOperatorPart;
import com.application.searching.queryLayer.QueryPart;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("not")
public class ProfileNot extends MappedProfilePart
{
	@NotNull
	MappedProfilePart child;
	
	public ProfileNot(){}
	
	public MappedProfilePart getChild(){return child;}
	
	public void setChild(MappedProfilePart child) 
	{
		this.child = child;
	}
	
	@Override
	public QueryPart<Long> map(SubPostRootHandler handler, QueryService service) 
	{
		QueryOperatorPart<Long> ret= new QueryOperatorPart<>(handler,SearchOperator.NOT);
		ret.addChild(child.map(handler, service));
		return null;
	}
	
}
