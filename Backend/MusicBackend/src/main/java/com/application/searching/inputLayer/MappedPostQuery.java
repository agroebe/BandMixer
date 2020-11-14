package com.application.searching.inputLayer;

import com.application.posts.Post;
import com.application.searching.QueryService;
import com.application.searching.queryLayer.PostQuery;

public class MappedPostQuery extends MappedQuery<Post> 
{
	private MappedPostPart base;
	
	@Override
	public PostQuery firstmap(QueryService service) 
	{
		//TODO
		return null;
	}

}
