package com.application.searching.queryLayer;

import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.criteriaLayer.SearchOperator;

public class OrPart<T> extends QueryOperatorPart<T> {

	public OrPart(RootHandler<T> handler) {
		super(handler, SearchOperator.OR);
	}

}
