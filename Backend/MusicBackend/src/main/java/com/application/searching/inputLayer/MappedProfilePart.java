package com.application.searching.inputLayer;

import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.queryLayer.QueryPart;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
	@JsonSubTypes.Type(value=MappedUsername.class),
	@JsonSubTypes.Type(value=MappedLocation.class),
	@JsonSubTypes.Type(value=ProfileAnd.class),
	@JsonSubTypes.Type(value=MappedApplication3.class),
	@JsonSubTypes.Type(value=ProfileNot.class),
	@JsonSubTypes.Type(value=ProfileOr.class)
})
public abstract class MappedProfilePart {
	public abstract QueryPart<Long> map(SubPostRootHandler handler, QueryService service);
}
