package com.application.searching;

public class QueryOperationPart<T> extends BasicQueryPart<T> 
{
	private QueryClass cls;
	private SearchOperation operation;
	private String field;
	private Object value;
	public QueryOperationPart(RootHandler<T> handler, QueryClass c, SearchOperation op, String field, Object val) 
	{
		super(handler);
		cls = c;
		operation = op;
		this.field = field;
		value = val;
	}
	
	@Override
	public SearchCriteria<T> generate() {
		return new BasicSearchCriteria<T>(field, value, operation, cls, handler);
	}

}
