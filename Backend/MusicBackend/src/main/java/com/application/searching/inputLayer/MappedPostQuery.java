package com.application.searching.inputLayer;

import com.application.posts.Post;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.queryLayer.Query;

public class MappedPostQuery implements MappedQuery<Post> {

	@Override
	public Query<Post> map(RootHandler<Post> handler, QueryService service) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query<Post> firstmap(QueryService service) 
	{
		return map(service.getPostHandler(),service);
	}

}
