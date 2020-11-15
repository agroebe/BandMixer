package com.application.searching.inputLayer;

import javax.validation.constraints.NotNull;

import com.application.posts.Post;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.PostRootHandler;
import com.application.searching.criteriaLayer.QueryClass;
import com.application.searching.criteriaLayer.SearchOperation;
import com.application.searching.criteriaLayer.SearchOperator;
import com.application.searching.queryLayer.QueryOperationPart;
import com.application.searching.queryLayer.QueryOperatorPart;
import com.application.searching.queryLayer.QueryPart;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("search")
public class MappedPostSearchingAllowed extends MappedPostPart
{

	@NotNull
	private Boolean acceptSearches;
	
	@NotNull
	private Boolean onlySearches;
	
	public MappedPostSearchingAllowed(){}
	
	public Boolean getAcceptSearches() {return acceptSearches;}
	
	public void setAcceptSearches(boolean acceptSearches) {this.acceptSearches = acceptSearches;}
	
	public Boolean getOnlySearches() {return onlySearches;}
	
	public void setOnlySearches(boolean onlySearches)
	{
		this.onlySearches = onlySearches;
	}
	
	@Override
	public QueryPart<Post> map(PostRootHandler handler, QueryService service) 
	{
		if(acceptSearches && onlySearches)
		{
			QueryOperationPart<Post> ret= new QueryOperationPart<>(handler,QueryClass.ePost,SearchOperation.NOT_EQUAL, "isSearch", (Integer)0);
			return ret;
		}
		else if(acceptSearches)
		{
			QueryOperationPart<Post> isSearch = new QueryOperationPart<>(handler,QueryClass.ePost,SearchOperation.NOT_EQUAL, "isSearch", (Integer)0);
			QueryOperationPart<Post> notSearch = new QueryOperationPart<>(handler,QueryClass.ePost,SearchOperation.EQUAL, "isSearch", (Integer)0);
			QueryOperatorPart<Post> either = new QueryOperatorPart<>(handler,SearchOperator.OR);
			either.addChild(isSearch);
			either.addChild(notSearch);
			return either;
		}
		else
		{
			QueryOperationPart<Post> ret= new QueryOperationPart<>(handler,QueryClass.ePost,SearchOperation.EQUAL, "isSearch", (Integer)0);
			return ret;
		}
	}

}
