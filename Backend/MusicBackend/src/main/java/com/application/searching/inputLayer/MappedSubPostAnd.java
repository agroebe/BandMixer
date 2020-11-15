package com.application.searching.inputLayer;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.SearchOperator;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.queryLayer.QueryOperatorPart;
import com.application.searching.queryLayer.QueryPart;

public class MappedSubPostAnd extends MappedSubPostPart {

	@NotEmpty
	private List<MappedSubPostPart> children;
	
	public MappedSubPostAnd()
	{
		children = new ArrayList<>();
	}
	
	public List<MappedSubPostPart> getChildren(){return children;}
	
	public void setChildren(List<MappedSubPostPart> children) 
	{
		this.children = (children == null ? new ArrayList<>() : children);
	}
	
	@Override
	public QueryPart<Long> map(SubPostRootHandler handler, QueryService service) {
		QueryOperatorPart<Long> ret= new QueryOperatorPart<>(handler,SearchOperator.AND);
		for(MappedSubPostPart p : children)
		{
			ret.addChild(p.map(handler, service));
		}
		return ret;
	}

}
