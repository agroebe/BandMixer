package com.application.posts;

import validation.annotations.ContentType;
import validation.annotations.ExistentPost;
import validation.annotations.ValidTitle;
import validation.ordergroups.First;
import validation.ordergroups.Second;

public class RequestUpdatePost 
{
	@ExistentPost
	private Long id;

	@ValidTitle(groups=First.class)
	private String title;
	
	@ContentType(groups=First.class)
	private String contentType;
	
	private String textContent;
	
	//private long contentId;
	
	private boolean isSearch;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = (title==null?null:title.trim());
	}
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String type) {
		this.contentType = (type==null?null:type.trim());
	}
	
	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = (textContent==null?null:textContent.trim());
	}

	public boolean getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}
}
