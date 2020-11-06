package com.application.searching;

import java.util.ArrayList;

public class QueryOperatorPart<T> extends BasicQueryPart<T> 
{
	private SearchOperator operator;
	protected ArrayList<QueryPart<T>> children;
	
	public QueryOperatorPart(RootHandler<T> handler, SearchOperator op) 
	{
		super(handler);
		operator = op;
		children = new ArrayList<>();
	}
	
	public void addChild(QueryPart<T> child)
	{
		children.add(child);
	}

	@Override
	public SearchCriteria<T> generate() 
	{
		CompositeSearchCriteria<T> ret = new CompositeSearchCriteria<T>(operator, handler);
		for(QueryPart<T> part : children)
		{
			ret.addChild(part.generate());
		}
		return ret;
	}

}
