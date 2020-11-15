package com.application.searching.inputLayer;

import javax.validation.constraints.NotNull;

import com.application.posts.Post;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.PostRootHandler;
import com.application.searching.criteriaLayer.UserRootHandler;
import com.application.searching.queryLayer.QueryPart;
import com.application.searching.queryLayer.UserSubquery;
import com.application.searching.queryLayer.UserSubqueryPart;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("owner")
public class MappedUserSub extends MappedPostPart
{
	@NotNull
	private MappedUserPart child;
	
	public MappedUserSub() {}
	
	public MappedUserPart getChild() {return child;}
	
	public void setChild(MappedUserPart child) {this.child = child;}
	
	@Override
	public QueryPart<Post> map(PostRootHandler handler, QueryService service) 
	{
		UserRootHandler h = handler.generateSubquery();
		UserSubquery q = new UserSubquery(h);
		q.setChild(this.child.map(h, service));
		UserSubqueryPart ret = new UserSubqueryPart(handler);
		ret.setChild(q);
		return ret;
	}
	
}
