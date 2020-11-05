package com.application.searching;

import com.application.posts.Post;

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
