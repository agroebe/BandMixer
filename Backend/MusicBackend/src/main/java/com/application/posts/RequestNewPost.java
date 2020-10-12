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

@GroupSequence({First.class, Second.class, Third.class})
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
	
	//private long contentId;
	
	private boolean isSearch;
	
	@UniqueList(groups=Third.class)
	private List< @ConvertGroup(to=Second.class) @Valid RequestTagApplication> applications;
	
	public RequestNewPost()
	{
		ownerid = -3;
		title = null;
		textContent = null;
		contentType = null;
		isSearch = false;
		applications = new ArrayList<RequestTagApplication>();
	}
	
	public long getOwnerId() {return ownerid;}
	public void setOwnerId(long id) {ownerid = id;}
	
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = (title==null?null:title.trim());}
	
	public String getContentType() {return contentType;}
	public void setContentType(String type) {this.contentType = (type==null?null:type.trim());}
	
	public String getTextContent() {return textContent;}
	public void setTextContent(String content) {this.textContent = (content==null?null:content.trim());}

	public boolean getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}

	public List<@Valid RequestTagApplication> getApplications() {
		return applications;
	}

	public void setApplications(List<@Valid RequestTagApplication> applications) {
		this.applications = (applications == null ? new ArrayList<RequestTagApplication>() : applications);
	}
	

}
