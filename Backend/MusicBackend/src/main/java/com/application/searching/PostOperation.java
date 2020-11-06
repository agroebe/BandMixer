package com.application.searching;

public class PostOperation<T> extends QueryOperationPart<T>
{

	public PostOperation(RootHandler<T> handler, SearchOperation op, String field, Object val) {
		super(handler, QueryClass.ePost, op, field, val);
	}

}
