package com.application.searching.inputLayer;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.application.posts.Post;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.PostRootHandler;
import com.application.searching.criteriaLayer.SearchOperator;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.queryLayer.QueryOperatorPart;
import com.application.searching.queryLayer.QueryPart;

public class MappedPostAnd extends MappedPostPart 
{
	@NotEmpty
	private List<MappedPostPart> children;
	
	public MappedPostAnd()
	{
		children = new ArrayList<>();
	}
	
	public List<MappedPostPart> getChildren(){return children;}
	
	public void setChildren(List<MappedPostPart> children) 
	{
		this.children = (children == null ? new ArrayList<>() : children);
	}
	
	@Override
	public QueryPart<Post> map(PostRootHandler handler, QueryService service) {
		QueryOperatorPart<Post> ret= new QueryOperatorPart<>(handler,SearchOperator.AND);
		for(MappedPostPart p : children)
		{
			ret.addChild(p.map(handler, service));
		}
		return ret;
	}
}
