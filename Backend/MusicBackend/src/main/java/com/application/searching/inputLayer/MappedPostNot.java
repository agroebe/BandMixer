package com.application.searching.inputLayer;

import javax.validation.constraints.NotNull;

import com.application.posts.Post;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.PostRootHandler;
import com.application.searching.criteriaLayer.SearchOperator;
import com.application.searching.queryLayer.QueryOperatorPart;
import com.application.searching.queryLayer.QueryPart;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("not")
public class MappedPostNot extends MappedPostPart
{
	@NotNull
	MappedPostPart child;
	
	public MappedPostNot(){}
	
	public MappedPostPart getChild(){return child;}
	
	public void setChild(MappedPostPart child) 
	{
		this.child = child;
	}
	
	@Override
	public QueryPart<Post> map(PostRootHandler handler, QueryService service) 
	{
		QueryOperatorPart<Post> ret= new QueryOperatorPart<>(handler,SearchOperator.NOT);
		ret.addChild(child.map(handler, service));
		return null;
	}
	
}
