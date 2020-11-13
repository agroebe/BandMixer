package com.application.searching.queryLayer;

import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.criteriaLayer.SearchOperator;

public class AndPart<T> extends QueryOperatorPart<T> {

	public AndPart(RootHandler<T> handler) {
		super(handler, SearchOperator.AND);
	}

}
