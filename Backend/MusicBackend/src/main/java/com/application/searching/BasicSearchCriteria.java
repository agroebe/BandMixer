package com.application.searching;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.application.people.User;
import com.application.posts.Post;

public class BasicSearchCriteria extends SearchCriteria {

	private String key;
    private Object value;
    private SearchOperation operation;
    
    public BasicSearchCriteria() {}
    
    public BasicSearchCriteria(String key, Object value, SearchOperation operation)
    {
    	
    }
    
	@Override
	public Predicate getPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		// TODO Auto-generated method stub
		ArrayList<User> a = new ArrayList<User>();
		//if(E)
		return null;
	}

	@Override
	public Predicate getPredicate2(Root<Post> post, CriteriaQuery<?> query, CriteriaBuilder builder) {
		// TODO Auto-generated method stub
		return null;
	}

}
