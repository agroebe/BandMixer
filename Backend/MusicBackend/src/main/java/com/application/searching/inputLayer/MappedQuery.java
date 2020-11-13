package com.application.searching.inputLayer;

import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.queryLayer.Query;

public interface MappedQuery<T>
{
	public Query<T> map(RootHandler<T> handler, QueryService service);
	public Query<T> firstmap(QueryService service);
}
