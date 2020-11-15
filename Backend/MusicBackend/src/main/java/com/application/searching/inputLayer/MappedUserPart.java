package com.application.searching.inputLayer;

import com.application.people.User;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.UserRootHandler;
import com.application.searching.queryLayer.QueryPart;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
	@JsonSubTypes.Type(value=MappedUserAnd.class),
	@JsonSubTypes.Type(value=MappedUserOr.class),
	@JsonSubTypes.Type(value=MappedUserNot.class),
	@JsonSubTypes.Type(value=MappedProfileQuery.class),
	@JsonSubTypes.Type(value=MappedSubPost.class)
})
public abstract class MappedUserPart 
{
	public abstract QueryPart<User> map(UserRootHandler handler, QueryService service);
}
