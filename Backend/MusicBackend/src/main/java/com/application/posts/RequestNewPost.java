package com.application.posts;

import java.util.ArrayList;
import java.util.List;

import com.application.tagging.RequestTagApplication;

import validation.annotations.ContentType;
import validation.annotations.ExistentOwner;
import validation.annotations.NullChecks;
import validation.annotations.UniqueList;
import validation.annotations.ValidTitle;
import validation.ordergroups.First;
import validation.ordergroups.Second;
import validation.ordergroups.Third;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;

/**
 * Data wrapper for a new post request
 */
@GroupSequence({First.class, Second.class, RequestNewPost.class, Third.class})
@NullChecks(fields= {"title", "contentType", "applications"}, groups=First.class)
public class RequestNewPost 
{
	@ExistentOwner(groups=Second.class)
	private long ownerid;
	
	@ValidTitle(groups=Second.class)
	private String title;
	
	@ContentType(groups=Second.class)
	private String contentType;
	
	private String textContent;
	
	private String contentPath;
	
	private boolean isSearch;
	
	@UniqueList(groups=Third.class)
	private List<RequestTagApplication> applications;

	/**
	 * Default constructor
	 */
	public RequestNewPost()
	{
		ownerid = -3;
		title = null;
		textContent = null;
		contentType = null;
		isSearch = false;
		applications = new ArrayList<RequestTagApplication>();
	}

	/**
	 *
	 * @return the owner id
	 */
	public long getOwnerId() {return ownerid;}

	/**
	 *
	 * @param id
	 */
	public void setOwnerId(long id) {ownerid = id;}

	/**
	 *
	 * @return the title of the post
	 */
	public String getTitle() {return title;}

	/**
	 *
	 * @param title
	 */
	public void setTitle(String title) {this.title = (title==null?null:title.trim());}

	/**
	 *
	 * @return the content type of the new post
	 */
	public String getContentType() {return contentType;}

	/**
	 *
	 * @param type
	 */
	public void setContentType(String type) {this.contentType = (type==null?null:type.trim());}

	/**
	 *
	 * @return the text content of the new post
	 */
	public String getTextContent() {return textContent;}

	/**
	 *
	 * @param content
	 */
	public void setTextContent(String content) {this.textContent = (content==null?null:content.trim());}

	/**
	 *
	 * @return the status of isSearch
	 */
	public boolean getIsSearch() {
		return isSearch;
	}

	/**
	 *
	 * @param isSearch
	 */
	public void setIsSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}

	/**
	 *
	 * @return a list of the applications of the tags
	 */
	public List<@Valid RequestTagApplication> getApplications() {
		return applications;
	}

	/**
	 *
	 * @param applications
	 */
	public void setApplications(List<@Valid RequestTagApplication> applications) {
		this.applications = (applications == null ? new ArrayList<RequestTagApplication>() : applications);
	}

	/**
	 *
	 * @return the content path
	 */
	public String getContentPath() {
		return contentPath;
	}

	/**
	 *
	 * @param contentPath
	 */
	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}
	

}
