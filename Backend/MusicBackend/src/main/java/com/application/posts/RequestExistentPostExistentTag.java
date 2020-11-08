package com.application.posts;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;


import com.application.tagging.RequestExistentTag;
import com.application.util.*;

import validation.annotations.ExistentPost;
import validation.annotations.MatchedTag;
import validation.ordergroups.First;
import validation.ordergroups.Fourth;
import validation.ordergroups.Second;
import validation.ordergroups.Third;

/**
 * Wrapper request class for existent posts
 */
@GroupSequence({First.class, Second.class, RequestExistentPostExistentTag.class, Third.class})
@NotNull(groups=First.class)
@MatchedTag(idfield="id", tagfield="tag", version=1, groups=Third.class)
public class RequestExistentPostExistentTag 
{
	@ExistentPost(groups=Second.class)
	private long id;
	
	@NotNull(groups=Second.class)
	private RequestExistentTag tag;

	/**
	 * Default constructor
	 */
	public RequestExistentPostExistentTag()
	{
		id = -3;
		tag = null;
	}

	/**
	 *
	 * @return the id of the existent post
	 */
	public long getId() {return id;}

	/**
	 *
	 * @param id
	 */
	public void setId(long id) {this.id = id;}

	/**
	 *
	 * @return the tag on this post
	 */
	public RequestExistentTag getTag() {return tag;}

	/**
	 *
	 * @param tag
	 */
	public void setTag(RequestExistentTag tag) {this.tag = tag;}
}
