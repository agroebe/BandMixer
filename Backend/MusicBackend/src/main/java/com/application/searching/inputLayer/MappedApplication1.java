package com.application.searching.inputLayer;

import com.application.posts.Post;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.PostRootHandler;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.queryLayer.QueryPart;

public class MappedApplication1 extends MappedPostPart
{
	private String tag;
	private String tagComp;
	private String operation;
	private String skill;
	private String skillComp;
	
	@Override
	public QueryPart<Post> map(PostRootHandler handler, QueryService service) {
		// TODO Auto-generated method stub
		return null;
	}

}
