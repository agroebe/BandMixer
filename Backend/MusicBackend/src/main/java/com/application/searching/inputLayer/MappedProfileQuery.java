package com.application.searching.inputLayer;

import javax.validation.constraints.NotNull;

import com.application.people.User;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.QueryClass;
import com.application.searching.criteriaLayer.SearchOperation;
import com.application.searching.criteriaLayer.SearchOperator;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.criteriaLayer.UserRootHandler;
import com.application.searching.queryLayer.PostSubquery;
import com.application.searching.queryLayer.PostSubqueryPart;
import com.application.searching.queryLayer.QueryOperationPart;
import com.application.searching.queryLayer.QueryOperatorPart;
import com.application.searching.queryLayer.QueryPart;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("has")
public class MappedProfileQuery extends MappedUserPart 
{
	@NotNull
	private MappedProfilePart child;
	
	public MappedProfileQuery() {}
	
	public MappedProfilePart getChild() {return child;}
	
	public void setChild(MappedProfilePart child) {this.child = child;}
	
	@Override
	public QueryPart<User> map(UserRootHandler handler, QueryService service) 
	{
		SubPostRootHandler h = handler.generatePostQuery();
		PostSubquery q = new PostSubquery(h);
		QueryOperatorPart<Long> child = new QueryOperatorPart<>(h, SearchOperator.AND);
		QueryOperationPart<Long> constraint = new QueryOperationPart<>(h,QueryClass.ePost,SearchOperation.EQUAL,"contentType","Profile");
		child.addChild(constraint);
		child.addChild(this.child.map(h, service));
		q.setChild(child);
		PostSubqueryPart ret = new PostSubqueryPart(handler);
		ret.setChild(q);
		return ret;
	}

}
