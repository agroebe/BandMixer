package com.posts;
import java.util.Set;

import javax.persistence.*;
import com.tagging.*;

@Entity
@Table(name="POSTS")
public class Post 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name="owner_id")
	private Long ownerId;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="content_type", nullable=false)
	private String contentType;
	
	@Column(name="text_content")
	private String textContent;
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="content_id")
	private Content content;
	
	@OneToMany(mappedBy="post")
	private Set<AppliedSkillLevel> tags;
	
	Post(){}
	
	public Post(String givenTitle, String type)
	{
		if(givenTitle == null)
		{
			throw new NullPointerException("Tag initialized with a null name.");
		}
		else if(type == null)
		{
			throw new NullPointerException("Tag initialized with a null name.");
		}
		title = givenTitle;
		contentType = type;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public Long getOwnerId()
	{
		return ownerId;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getContentType()
	{
		return contentType;
	}
	
	public String getTextContent()
	{
		return textContent;
	}
	
	public Set<AppliedSkillLevel> getAppliedTags()
	{
		return tags;
	}
	
	public void setTextContent(String text)
	{
		textContent = text;
	}
}
