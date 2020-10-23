package com.application.posts;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import validation.annotations.ExistentPost;
import validation.ordergroups.First;
import validation.ordergroups.Second;

@GroupSequence({RequestPost.class, First.class,Second.class})
@NotNull(groups= {First.class})
public class RequestPost 
{
	@ExistentPost(groups=Second.class)
	private long id;
	
	public RequestPost()
	{
		id = -3;
	}
	
	public RequestPost(long id)
	{
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
