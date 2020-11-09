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

/**
 * Data wrapper for adding new tags to existent posts
 */
@UnmatchedTag(idfield = "id", tagfield = "application", groups=Third.class)
@NullChecks(fields= {"application"} ,groups=First.class)
public class RequestExistentPostNewTag 
{
	@ExistentPost(groups=Second.class)
	private long id;
	
	private RequestTagApplication application;

	/**
	 * Default constructor
	 */
	public RequestExistentPostNewTag() 
	{
		id = -3;
		application = null;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(long id) {this.id = id;}

	/**
	 *
	 * @return the id of the new tag
	 */
	public long getId() {return id;}

	/**
	 *
	 * @param application
	 */
	public void setApplication(RequestTagApplication application) {this.application = application;}
	public RequestTagApplication getApplication() {return application;}
}
