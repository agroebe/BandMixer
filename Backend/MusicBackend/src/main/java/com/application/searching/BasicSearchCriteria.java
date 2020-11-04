package com.application.searching;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.application.people.User;
import com.application.posts.Post;

public class BasicSearchCriteria extends SearchCriteria {

	private String key;
    private Object value;
    private SearchOperation operation;
    private QueryType myType;
    private QueryClass myCls;
    
    public BasicSearchCriteria() {}
    
    public BasicSearchCriteria(String key, Object value, SearchOperation operation, QueryType type, QueryClass cls)
    {
    	this.key = key;
    	this.value = value;
    	this.operation = operation;
    	myType = type;
    	myCls = cls;
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public Predicate getPredicate(RootHandler root) 
	{
		CriteriaBuilder cb = root.getBuilder();
		From actualRoot = root.getFrom(myType, myCls);
		switch(operation)
		{
			case GREATER_THAN:
			{
				return cb.greaterThan(actualRoot.get(key), value.toString());
			}
			case GREATER_THAN_EQUAL:
			{
				return cb.greaterThanOrEqualTo(actualRoot.get(key), value.toString());
			}
			case LESS_THAN:
			{
				return cb.lessThan(actualRoot.get(key), value.toString());
			}
			case LESS_THAN_EQUAL:
			{
				return cb.lessThanOrEqualTo(actualRoot.get(key), value.toString());
			}
			case NOT_EQUAL:
			{
				return cb.notEqual(actualRoot.get(key), value);
			}
			case EQUAL:
			{
				return cb.equal(actualRoot.get(key), value);
			}
			case MATCH:
			{
				return cb.like(cb.lower(actualRoot.get(key)), "%"+ value.toString().toLowerCase() + "%");
			}
			case MATCH_START:
			{
				return cb.like(cb.lower(actualRoot.get(key)), "%"+ value.toString().toLowerCase());
			}
			case MATCH_END:
			{
				return cb.like(cb.lower(actualRoot.get(key)), value.toString().toLowerCase() + "%");
			}
			case IN:
			{
				return cb.in(actualRoot.get(key)).value(value);
			}
			case NOT_IN:
			{
				return cb.not(actualRoot.get(key)).in(value);
			}
			default:
			{
				throw new IllegalArgumentException("Invalid operation for criteria.");
			}
		}
	}

	

}
