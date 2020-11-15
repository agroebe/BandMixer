package com.application.searching.inputLayer;

import com.application.posts.Post;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.PostRootHandler;
import com.application.searching.queryLayer.QueryPart;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
	@JsonSubTypes.Type(value=MappedPostId.class),
	@JsonSubTypes.Type(value=MappedPostContentType.class),
	@JsonSubTypes.Type(value=MappedPostAnd.class),
	@JsonSubTypes.Type(value=MappedApplication1.class),
	@JsonSubTypes.Type(value=MappedPostNot.class),
	@JsonSubTypes.Type(value=MappedPostOr.class),
	@JsonSubTypes.Type(value=MappedPostSearchingAllowed.class),
	@JsonSubTypes.Type(value=MappedPostText.class),
	@JsonSubTypes.Type(value=MappedUserSub.class),
	@JsonSubTypes.Type(value=MappedPostTitle.class)
})
public abstract class MappedPostPart 
{
	public abstract QueryPart<Post> map(PostRootHandler handler, QueryService service);
}
