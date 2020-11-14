package com.application.searching.queryLayer;

import com.application.posts.Post;
import com.application.searching.criteriaLayer.BasicSearchCriteria;
import com.application.searching.criteriaLayer.QueryClass;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.criteriaLayer.SearchCriteria;
import com.application.searching.criteriaLayer.SearchOperation;

public abstract class SubqueryPart<R,F> extends QueryPart<R> {

	private MySubquery<F> child;
	private String field;
	private QueryClass cls;
	
	protected SubqueryPart(RootHandler<R> handler, String field, QueryClass cls) 
	{
		super(handler);
		this.field = field;
		this.cls = cls;
	}

	public void setChild(MySubquery<F> c)
	{
		child = c;
	}

	@Override
	public SearchCriteria<R> generate() {
		return new BasicSearchCriteria<R>(field,child.formulate(),SearchOperation.IN,cls, handler);
	}
}
