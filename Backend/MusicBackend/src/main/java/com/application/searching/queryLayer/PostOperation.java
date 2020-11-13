package com.application.searching.queryLayer;

import com.application.searching.criteriaLayer.QueryClass;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.criteriaLayer.SearchOperation;

public class PostOperation<T> extends QueryOperationPart<T>
{

	public PostOperation(RootHandler<T> handler, SearchOperation op, String field, Object val) {
		super(handler, QueryClass.ePost, op, field, val);
	}

}
