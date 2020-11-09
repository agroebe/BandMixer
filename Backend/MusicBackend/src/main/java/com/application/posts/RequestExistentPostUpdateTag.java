package com.application.posts;

import javax.validation.GroupSequence;

import com.application.tagging.RequestTagApplication;

import validation.annotations.ExistentPost;
import validation.annotations.MatchedTag;
import validation.annotations.NullChecks;
import validation.annotations.UpdatedTag;
import validation.ordergroups.First;
import validation.ordergroups.Fourth;
import validation.ordergroups.Second;
import validation.ordergroups.Third;

/**
 * Data wrapper for updating an existent tag on an existent post
 */
@GroupSequence({First.class, Second.class, RequestExistentPostUpdateTag.class,Third.class, Fourth.class})
@NullChecks(fields= {"application"}, groups=First.class)
@MatchedTag(idfield = "id", tagfield = "application", version = 2, groups=Third.class)
@UpdatedTag(idfield = "id", tagfield = "application", groups=Fourth.class)
public class RequestExistentPostUpdateTag 
{
	@ExistentPost(groups=Second.class)
	private long id;
	
	private RequestTagApplication application;

	/**
	 * Default constructor
	 */
	public RequestExistentPostUpdateTag() 
	{
		id = -3;
	}

	/**
	 *
	 * @return the Id of the tag
	 */
	public long getId() {
		return id;
	}

	/**
	 *
	 * @param id
	 */
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
