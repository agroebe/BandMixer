package com.application.posts;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;

import com.application.tagging.RequestTagApplication;
import com.application.util.*;

import validation.annotations.ExistentPost;
import validation.annotations.MatchedTag;
import validation.annotations.NullChecks;
import validation.annotations.UpdatedTag;
import validation.ordergroups.Fifth;
import validation.ordergroups.First;
import validation.ordergroups.Fourth;
import validation.ordergroups.Second;
import validation.ordergroups.Third;

@GroupSequence({First.class, Second.class, Third.class, Fourth.class, Fifth.class})
@NullChecks(fields= {"application"}, groups=First.class)
@MatchedTag(idfield = "id", tagfield = "application", version = 2, groups=Fourth.class)
@UpdatedTag(idfield = "id", tagfield = "application", groups=Fifth.class)
public class RequestExistentPostUpdateTag 
{
	@ExistentPost(groups=Second.class)
	private long id;
	
	@Valid
	@ConvertGroup(to=Third.class)
	private RequestTagApplication application;
	
	public RequestExistentPostUpdateTag() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RequestTagApplication getApplication() {
		return application;
	}

	public void setApplication(RequestTagApplication application) {
		this.application = application;
	}
}
