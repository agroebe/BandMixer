package com.application.searching.inputLayer;

import com.application.people.User;
import com.application.posts.Post;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.PostRootHandler;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.queryLayer.QueryPart;

public abstract class MappedPostPart 
{
	public abstract QueryPart<Post> map(PostRootHandler handler, QueryService service);
}
