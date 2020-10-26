package com.application.posts;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.application.View;
import com.application.people.User;
import com.application.skill_level.AppliedSkillLevel;
import com.application.skill_level.AppliedSkillLevelRepository;
import com.application.skill_level.SkillLevel;
import com.application.tagging.*;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="POSTS")
public class Post 
{
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class})
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User owner;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
	@Column(name="title", nullable=false)
	private String title;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
	@Column(name="content_type", nullable=false)
	private String contentType;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
	@Column(name="text_content")
	private String textContent;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
	@Column(name="content_path")
	private String contentPath;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
	@Column(name="is_search", nullable=false)
	private Integer isSearch;
	
	@JsonView({View.PostView.class, View.UserView.class})
	@OneToMany(mappedBy="post")
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
		this.tags = new HashMap<TagSkillLevelKey, AppliedSkillLevel>();
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
		this.tags = new HashMap<TagSkillLevelKey, AppliedSkillLevel>();
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
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public User getOwner()
	{
		return owner;
	}
	
	public void setOwner(User owner)
	{
		this.owner = owner;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getContentType()
	{
		return contentType;
	}
	
	public void setContentType(String type)
	{
		this.contentType = type;
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
		return addTag(rep, tag, level, false, false);
	}
	
	public String getContentPath() {return contentPath;}
	
	public void setContentPath(String contentPath) {this.contentPath = contentPath;}
	
	public boolean addTag(AppliedSkillLevelRepository rep, Tag tag, SkillLevel level, 
			boolean bounded, boolean lowerbounded)
	{
		if(tags.containsKey(new TagSkillLevelKey(this.id,tag.getId())))
		{
			return false;
		}
		AppliedSkillLevel app = new AppliedSkillLevel(this, tag, level, bounded, lowerbounded);
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
		ArrayList<AppliedSkillLevel> vals = new ArrayList<AppliedSkillLevel>(tags.values());
		for(AppliedSkillLevel tag :vals)
		{
			tag.remove(rep);
		}
		if(rep != null)
		{
			myRep.delete(this);
		}
	}
	
	public boolean updateTag(AppliedSkillLevelRepository rep, Tag tag, SkillLevel level, boolean isbounded, boolean lowerbounded)
	{
		TagSkillLevelKey key = new TagSkillLevelKey(this.id,tag.getId());
		if(!tags.containsKey(key))
		{
			return false;
		}
		AppliedSkillLevel app = tags.get(key);
		app.setSkillLevel(level);
		app.setIsBounded(isbounded);
		app.setIsLowerBound(lowerbounded);
		rep.save(app);
		return true;
	}
}
