package com.application.searching.inputLayer;

import javax.validation.constraints.NotNull;

import com.application.people.User;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.criteriaLayer.UserRootHandler;
import com.application.searching.queryLayer.PostSubquery;
import com.application.searching.queryLayer.PostSubqueryPart;
import com.application.searching.queryLayer.QueryPart;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("post")
public class MappedSubPost extends MappedUserPart 
{
	@NotNull
	private MappedSubPostPart child;
	
	public MappedSubPost() {}
	
	public MappedSubPostPart getChild() {return child;}
	
	public void setChild(MappedSubPostPart child) {this.child = child;}
	
	@Override
	public QueryPart<User> map(UserRootHandler handler, QueryService service) 
	{
		SubPostRootHandler h = handler.generatePostQuery();
		PostSubquery q = new PostSubquery(h);
		q.setChild(this.child.map(h, service));
		PostSubqueryPart ret = new PostSubqueryPart(handler);
		ret.setChild(q);
		return ret;
	}

}
