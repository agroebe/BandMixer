package com.application.searching.inputLayer;

import javax.validation.constraints.NotNull;

import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.QueryClass;
import com.application.searching.criteriaLayer.SearchOperation;
import com.application.searching.criteriaLayer.SearchOperator;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.queryLayer.QueryOperationPart;
import com.application.searching.queryLayer.QueryOperatorPart;
import com.application.searching.queryLayer.QueryPart;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("search")
public class MappedSubPostSearchingAllowed extends MappedSubPostPart
{

	@NotNull
	private Boolean acceptSearches;
	
	@NotNull
	private Boolean onlySearches;
	
	public MappedSubPostSearchingAllowed(){}
	
	public Boolean getAcceptSearches() {return acceptSearches;}
	
	public void setAcceptSearches(boolean acceptSearches) {this.acceptSearches = acceptSearches;}
	
	public Boolean getOnlySearches() {return onlySearches;}
	
	public void setOnlySearches(boolean onlySearches)
	{
		this.onlySearches = onlySearches;
	}
	
	@Override
	public QueryPart<Long> map(SubPostRootHandler handler, QueryService service) 
	{
		if(acceptSearches && onlySearches)
		{
			QueryOperationPart<Long> ret= new QueryOperationPart<>(handler,QueryClass.ePost,SearchOperation.NOT_EQUAL, "isSearch", (Integer)0);
			return ret;
		}
		else if(acceptSearches)
		{
			QueryOperationPart<Long> isSearch = new QueryOperationPart<>(handler,QueryClass.ePost,SearchOperation.NOT_EQUAL, "isSearch", (Integer)0);
			QueryOperationPart<Long> notSearch = new QueryOperationPart<>(handler,QueryClass.ePost,SearchOperation.EQUAL, "isSearch", (Integer)0);
			QueryOperatorPart<Long> either = new QueryOperatorPart<>(handler,SearchOperator.OR);
			either.addChild(isSearch);
			either.addChild(notSearch);
			return either;
		}
		else
		{
			QueryOperationPart<Long> ret= new QueryOperationPart<>(handler,QueryClass.ePost,SearchOperation.EQUAL, "isSearch", (Integer)0);
			return ret;
		}
	}

}
