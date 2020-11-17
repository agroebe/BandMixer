package com.application.searching.inputLayer;

import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.queryLayer.QueryPart;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
	@JsonSubTypes.Type(value=MappedSubPostId.class),
	@JsonSubTypes.Type(value=MappedSubPostContentType.class),
	@JsonSubTypes.Type(value=MappedSubPostAnd.class),
	@JsonSubTypes.Type(value=MappedApplication2.class),
	@JsonSubTypes.Type(value=MappedSubPostNot.class),
	@JsonSubTypes.Type(value=MappedSubPostOr.class),
	@JsonSubTypes.Type(value=MappedSubPostSearchingAllowed.class),
	@JsonSubTypes.Type(value=MappedSubPostText.class),
	@JsonSubTypes.Type(value=MappedSubPostTitle.class)
})
public abstract class MappedSubPostPart 
{
	public abstract QueryPart<Long> map(SubPostRootHandler handler, QueryService service);
}
