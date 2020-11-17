package com.application.searching.inputLayer;

import javax.validation.constraints.NotNull;

import com.application.posts.Post;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.PostRootHandler;
import com.application.searching.queryLayer.PostQuery;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("post")
public class MappedPostQuery extends MappedQuery<Post> 
{
	@NotNull
	private MappedPostPart child;
	
	public MappedPostQuery() {}
	
	public MappedPostPart getChild() {return child;}
	
	public void setChild(MappedPostPart child) {this.child = child;}
	

	@Override
	public PostQuery firstmap(QueryService service) 
	{
		PostRootHandler h = (PostRootHandler)service.getPostHandler();
		PostQuery q = new PostQuery(h);
		q.setChild(this.child.map(h, service));
		return q;
	}
}
