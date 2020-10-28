package com.application.posts;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import validation.annotations.ContentType;
import validation.annotations.ExistentPost;
import validation.ordergroups.First;
import validation.ordergroups.Second;

@GroupSequence({RequestPost.class, First.class,Second.class})
@NotNull(groups= {First.class})
public class RequestPostType 
{
	@ExistentPost(groups=Second.class)
	private long id;
	
	@ContentType(groups=Second.class)
	private String contentType;
	
	public RequestPostType()
	{
		id = -3;
	}
	
	public RequestPostType(long id)
	{
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getContentType() {return contentType;}
	public void setContentType(String type) {contentType = type;}
}