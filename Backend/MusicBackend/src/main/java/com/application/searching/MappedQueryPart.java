package com.application.searching;

import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.queryLayer.QueryPart;

public abstract class MappedQueryPart<T> 
{
	public abstract QueryPart<T> map(RootHandler<T> handler);
}
