package com.application.posts;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;

import com.application.skill_level.AppliedSkillLevel;
import com.application.skill_level.AppliedSkillLevelRepository;
import com.application.skill_level.SkillLevel;
import com.application.tagging.*;

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
	
	@Column(name="is_search", nullable=false)
	private Integer isSearch;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="post")
	@MapKey(name="id")
	private Map<TagSkillLevelKey, AppliedSkillLevel> tags;
	
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
		this.isSearch = 0;
	}
	
	public Post(String givenTitle, String type, boolean isSearch)
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
		this.isSearch = (isSearch ? 1 : 0);
	}
	
	public Boolean getIsSearch()
	{
		return (isSearch==0? false : true);
	}
	
	public void setIsSearch(boolean isSearch)
	{
		this.isSearch = (isSearch ? 1 : 0);
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
	
	public Map<TagSkillLevelKey, AppliedSkillLevel> getAppliedTags()
	{
		return tags;
	}
	
	public void setTextContent(String text)
	{
		textContent = text;
	}
	
	public void setTitle(String newTitle)
	{
		title = newTitle;
	}
	
	public boolean addTag(AppliedSkillLevelRepository rep, Tag tag, SkillLevel level)
	{
		if(tags.containsKey(new TagSkillLevelKey(this.id,tag.getId())))
		{
			return false;
		}
		AppliedSkillLevel app = new AppliedSkillLevel(this, tag, level);
		rep.save(app);
		return true;
	}
	
	public boolean addTag(AppliedSkillLevelRepository rep, Tag tag)
	{
		return addTag(rep, tag, null);
	}
	
	public boolean removeTag(AppliedSkillLevelRepository rep, Tag tag)
	{
		TagSkillLevelKey key = new TagSkillLevelKey(this.id,tag.getId());
		if(!tags.containsKey(key))
		{
			return false;
		}
		AppliedSkillLevel level = tags.get(key);
		level.remove(rep);
		return true;
	}
	
	public void remove(AppliedSkillLevelRepository rep, PostRepository myRep)
	{
		for(AppliedSkillLevel tag : tags.values())
		{
			tag.remove(rep);
		}
		if(rep != null)
		{
			myRep.delete(this);
		}
	}
	
	public boolean setTagSkill(Tag tag, SkillLevel level)
	{
		TagSkillLevelKey key = new TagSkillLevelKey(this.id,tag.getId());
		if(!tags.containsKey(key))
		{
			return false;
		}
		tags.get(key).setSkillLevel(level);
		return true;
	}
}
