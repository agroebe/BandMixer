package com.application.searching.inputLayer;

import com.application.searching.MappedQueryPart;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.queryLayer.Query;

public abstract class MappedQuery<T>
{
	protected MappedQueryPart<T> child;
	public abstract Query<T> firstmap(QueryService service);
}
