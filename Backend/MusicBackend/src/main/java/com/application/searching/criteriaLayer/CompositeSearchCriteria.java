package com.application.searching.criteriaLayer;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public class CompositeSearchCriteria<T> extends SearchCriteria<T> 
{
	private SearchOperator operator;
	private ArrayList<SearchCriteria<T>> children;
	
	public CompositeSearchCriteria(SearchOperator op, RootHandler<T> handler) 
	{
		super(handler);
		operator = op;
		children = new ArrayList<>();
	}
	
	public CompositeSearchCriteria(SearchOperator op, RootHandler<T> handler, SearchCriteria<T> criterias[]) 
	{
		super(handler);
		operator = op;
		children = new ArrayList<>();
		for(SearchCriteria<T> c : criterias)
		{
			children.add(c);
		}
	}
	
	public void addChild(SearchCriteria<T> c)
	{
		children.add(c);
	}
	
	@Override
	public Predicate getPredicate() {
		CriteriaBuilder cb = handler.getBuilder();
		Predicate[] preds = new Predicate[children.size()];
		int i = 0;
		for(SearchCriteria<T> c : children)
		{
			preds[i++] = c.getPredicate();
		}
		switch(operator)
		{
			case AND:
			{
				return cb.and(preds);
			}
			case OR:
			{
				return cb.or(preds);
			}
			case NOT:
			{
				if(preds.length != 1)
				{
					throw new IllegalStateException("Cannot apply a NOT to more or less than one predicate");
				}
				return cb.not(preds[0]);
			}
			default:
			{
				throw new IllegalArgumentException("Operator is not supported");
			}
		}
	}

}
