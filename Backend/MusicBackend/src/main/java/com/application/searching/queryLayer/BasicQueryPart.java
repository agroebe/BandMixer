package com.application.searching.queryLayer;

import com.application.searching.criteriaLayer.RootHandler;

public abstract class BasicQueryPart<T> extends QueryPart<T>
{

	protected BasicQueryPart(RootHandler<T> handler) {
		super(handler);
	}


}
