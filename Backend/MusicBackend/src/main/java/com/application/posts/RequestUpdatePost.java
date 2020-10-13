package com.application.posts;

import javax.validation.GroupSequence;

import validation.annotations.ContentType;
import validation.annotations.ExistentPost;
import validation.annotations.NullChecks;
import validation.annotations.UpdatedPost;
import validation.annotations.ValidTitle;
import validation.ordergroups.First;
import validation.ordergroups.Second;
import validation.ordergroups.Third;

@GroupSequence({First.class,Second.class,Third.class})
@NullChecks(fields= {}, groups=First.class)
@UpdatedPost(groups=Third.class, idfield = "id", searchField = "isSearch", 
	textContentField = "textContent", titlefield = "title", typefield = "contentType")
public class RequestUpdatePost 
{
	@ExistentPost(groups=Second.class)
	private Long id;

	@ValidTitle(groups=Second.class)
	private String title;
	
	@ContentType(groups=Second.class)
	private String contentType;
	
	private String textContent;
	
	//private long contentId;
	
	private boolean isSearch;
	
	public RequestUpdatePost()
	{
		id = -3L;
		title = null;
		contentType = null;
		textContent = null;
		isSearch = false;
	}
	
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
