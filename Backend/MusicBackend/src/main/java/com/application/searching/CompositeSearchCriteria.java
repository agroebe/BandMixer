package com.application.searching;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public class CompositeSearchCriteria extends SearchCriteria 
{
	private SearchOperator operator;
	private ArrayList<SearchCriteria> children;
	
	public CompositeSearchCriteria(SearchOperator op) 
	{
		operator = op;
		children = new ArrayList<>();
	}
	
	public CompositeSearchCriteria(SearchOperator op, SearchCriteria ...criterias) 
	{
		operator = op;
		children = new ArrayList<>();
		for(SearchCriteria c : criterias)
		{
			children.add(c);
		}
	}
	
	public void addChild(SearchCriteria c)
	{
		children.add(c);
	}
	
	@Override
	public Predicate getPredicate(RootHandler root) {
		CriteriaBuilder cb = root.getBuilder();
		Predicate[] preds = new Predicate[children.size()];
		int i = 0;
		for(SearchCriteria c : children)
		{
			preds[i++] = c.getPredicate(root);
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
