package com.application.searching;

public class NotPart<T> extends QueryOperatorPart<T> {

	public NotPart(RootHandler<T> handler) {
		super(handler, SearchOperator.NOT);
	}

	@Override
	public void addChild(QueryPart<T> child)
	{
		if(children.size() == 0)
		{
			children.add(child);
		}
		else
		{
			children.set(0,child);
		}
	}
}
