package com.application.searching.queryLayer;

import com.application.posts.Post;
import com.application.searching.criteriaLayer.BasicSearchCriteria;
import com.application.searching.criteriaLayer.QueryClass;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.criteriaLayer.SearchCriteria;
import com.application.searching.criteriaLayer.SearchOperation;

public class Subquery extends QueryPart<Post>
{
	
	private UserQuery child;
	
	public Subquery(RootHandler<Post> handler) {
		super(handler);
	}
	
	public void setChild(UserQuery c)
	{
		child = c;
	}

	@Override
	public SearchCriteria<Post> generate() {
		return new BasicSearchCriteria<Post>("owner",child.formulate(),SearchOperation.IN,QueryClass.ePost, handler);
	}

}
