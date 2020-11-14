package com.application.searching.queryLayer;

import java.util.ArrayList;

import com.application.searching.criteriaLayer.CompositeSearchCriteria;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.criteriaLayer.SearchCriteria;
import com.application.searching.criteriaLayer.SearchOperator;

public class QueryOperatorPart<R> extends QueryPart<R> 
{
	private SearchOperator operator;
	protected ArrayList<QueryPart<R>> children;
	
	public QueryOperatorPart(RootHandler<R> handler, SearchOperator op) 
	{
		super(handler);
		operator = op;
		children = new ArrayList<>();
	}
	
	public void addChild(QueryPart<R> child)
	{
		children.add(child);
	}

	@Override
	public SearchCriteria<R> generate() 
	{
		CompositeSearchCriteria<R> ret = new CompositeSearchCriteria<R>(operator, handler);
		for(QueryPart<R> part : children)
		{
			ret.addChild(part.generate());
		}
		return ret;
	}

}
