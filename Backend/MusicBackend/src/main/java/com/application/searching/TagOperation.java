package com.application.searching;

public class TagOperation<T> extends QueryOperationPart<T>
{

	public TagOperation(RootHandler<T> handler, SearchOperation op, String field, Object val) {
		super(handler, QueryClass.eTag, op, field, val);
	}

}