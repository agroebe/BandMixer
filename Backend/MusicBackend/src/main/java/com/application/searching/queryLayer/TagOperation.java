package com.application.searching.queryLayer;

import com.application.searching.criteriaLayer.QueryClass;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.criteriaLayer.SearchOperation;

public class TagOperation<T> extends QueryOperationPart<T>
{

	public TagOperation(RootHandler<T> handler, SearchOperation op, String field, Object val) {
		super(handler, QueryClass.eTag, op, field, val);
	}

}