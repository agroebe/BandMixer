package com.application.searching.criteriaLayer;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

public class BasicSearchCriteria<T> extends SearchCriteria<T> {

	private String key;
    private Object value;
    private SearchOperation operation;
    private QueryClass myCls;
    
    
    public BasicSearchCriteria(String key, Object value, SearchOperation operation, QueryClass cls, RootHandler<T> handler)
    {
    	super(handler);
    	this.key = key;
    	this.value = value;
    	this.operation = operation;
    	myCls = cls;
    }
    
    
	@Override
	public Predicate getPredicate() 
	{
		CriteriaBuilder cb = handler.getBuilder();
		From<?,?> actualRoot = handler.getFrom(myCls);
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
				return cb.in(actualRoot.get(key)).value((Expression<?>)value);
			}
			case NOT_IN:
			{
				return cb.not(actualRoot.get(key)).in((Expression<?>)value);
			}
			default:
			{
				throw new IllegalArgumentException("Invalid operation for criteria.");
			}
		}
	}

	

}
