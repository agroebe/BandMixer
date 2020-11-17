package com.application.searching.inputLayer;

import com.application.searching.MappedQueryPart;
import com.application.searching.QueryService;
import com.application.searching.queryLayer.Query;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
	@JsonSubTypes.Type(value=MappedPostQuery.class),
	@JsonSubTypes.Type(value=MappedUserQuery.class)
})
public abstract class MappedQuery<T>
{
	protected MappedQueryPart<T> child;
	public abstract Query<T> firstmap(QueryService service);
}
