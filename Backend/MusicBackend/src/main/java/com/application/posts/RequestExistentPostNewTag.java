package com.application.posts;

import javax.validation.GroupSequence;

import com.application.tagging.RequestTagApplication;

import validation.annotations.ExistentPost;
import validation.annotations.NullChecks;
import validation.annotations.UnmatchedTag;
import validation.ordergroups.First;
import validation.ordergroups.Second;
import validation.ordergroups.Third;

@GroupSequence({First.class,Second.class,RequestExistentPostNewTag.class,Third.class})

@UnmatchedTag(idfield = "id", tagfield = "application", groups=Third.class)
@NullChecks(fields= {"application"} ,groups=First.class)
public class RequestExistentPostNewTag 
{
	@ExistentPost(groups=Second.class)
	private long id;
	
	private RequestTagApplication application;
	
	public RequestExistentPostNewTag() 
	{
		id = -3;
		application = null;
	}
	
	public void setId(long id) {this.id = id;}
	public long getId() {return id;}
	public void setApplication(RequestTagApplication application) {this.application = application;}
	public RequestTagApplication getApplication() {return application;}
}
