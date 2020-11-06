package com.application.searching;

public class AndPart<T> extends QueryOperatorPart<T> {

	public AndPart(RootHandler<T> handler) {
		super(handler, SearchOperator.AND);
	}

}
