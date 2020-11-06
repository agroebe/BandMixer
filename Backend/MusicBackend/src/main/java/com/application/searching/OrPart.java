package com.application.searching;

public class OrPart<T> extends QueryOperatorPart<T> {

	public OrPart(RootHandler<T> handler) {
		super(handler, SearchOperator.OR);
	}

}
