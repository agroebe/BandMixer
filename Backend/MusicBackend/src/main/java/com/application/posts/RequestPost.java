package com.application.posts;

@ExistentPost
public class RequestPost 
{
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
