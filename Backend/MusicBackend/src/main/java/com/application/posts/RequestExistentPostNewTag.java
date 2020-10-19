package com.application.posts;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;

import com.application.tagging.RequestTagApplication;
import com.application.util.*;

import validation.annotations.ExistentPost;
import validation.annotations.NullChecks;
import validation.annotations.UnmatchedTag;
import validation.ordergroups.First;
import validation.ordergroups.Fourth;
import validation.ordergroups.Second;
import validation.ordergroups.Third;

@GroupSequence({RequestExistentPostNewTag.class, First.class,Second.class,Third.class, Fourth.class})
@NullChecks(fields= {"application"} ,groups=First.class)
@UnmatchedTag(idfield = "id", tagfield = "application", groups=Fourth.class)
public class RequestExistentPostNewTag 
{
	@ExistentPost(groups=Second.class)
	private long id;
	
	@Valid
	@ConvertGroup(to=Third.class)
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
