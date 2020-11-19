package com.application.posts;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.application.View;
import com.application.people.User;
import com.application.people.UserRepository;
import com.application.skill_level.AppliedSkillLevel;
import com.application.skill_level.AppliedSkillLevelRepository;
import com.application.skill_level.SkillLevel;
import com.application.tagging.*;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Post class
 */

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Table(name="POSTS")
public class Post 
{
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class,View.QueryView.class})
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class,View.QueryView.class})
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User owner;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class,View.QueryView.class})
	@Column(name="title", nullable=false)
	private String title;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class,View.QueryView.class})
	@Column(name="content_type", nullable=false)
	private String contentType;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class,View.QueryView.class})
	@Column(name="text_content")
	private String textContent;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class,View.QueryView.class})
	@Column(name="content_path")
	private String contentPath;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class,View.QueryView.class})
	@Column(name="is_search", nullable=false)
	private Integer isSearch;
	
	@JsonView({View.PostView.class, View.UserView.class})
	@OneToMany(mappedBy="post")
	@MapKey(name="id")
	private Map<TagSkillLevelKey, AppliedSkillLevel> tags;

	/**
	 * Default constructor
	 */
	public Post(){}

	/**
	 * Constructor that accepts a title and the type of post it is.
	 * @param givenTitle
	 * @param type
	 */
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

	/**
	 * Constructor that accepts a title and type as well as the isSearch parameter.
	 * @param givenTitle
	 * @param type
	 * @param isSearch
	 */
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

	/**
	 *
	 * @return the status of the isSearch variable
	 */
	public Boolean getIsSearch()
	{
		return (isSearch==0? false : true);
	}

	/**
	 * Toggles the isSearch variable
	 * @param isSearch
	 */
	public void setIsSearch(boolean isSearch)
	{
		this.isSearch = (isSearch ? 1 : 0);
	}

	/**
	 *
	 * @return the Post id
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 *
	 * @return the owner of the Post
	 */
	public User getOwner()
	{
		return owner;
	}

	/**
	 *
	 * @param owner
	 */
	public void setOwner(User owner)
	{
		this.owner = owner;
	}

	/**
	 *
	 * @return the title of the Post
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 *
	 * @return the content type of the Post
	 */
	public String getContentType()
	{
		return contentType;
	}

	/**
	 *
	 * @param type
	 */
	public void setContentType(String type)
	{
		this.contentType = type;
	}

	/**
	 *
	 * @return the text content of the Post
	 */
	public String getTextContent()
	{
		return textContent;
	}

	/**
	 *
	 * @return a list of the tags that have been applied to this Post
	 */
	public Map<TagSkillLevelKey, AppliedSkillLevel> getAppliedTags()
	{
		return tags;
	}

	/**
	 *
	 * @param text
	 */
	public void setTextContent(String text)
	{
		textContent = text;
	}

	/**
	 *
	 * @param newTitle
	 */
	public void setTitle(String newTitle)
	{
		title = newTitle;
	}

	/**
	 * Adds a tag to this post that is unbounded.
	 * @param rep
	 * @param tag
	 * @param level
	 * @return true if the tag was added, false otherwise
	 */
	public boolean addTag(AppliedSkillLevelRepository rep, Tag tag, SkillLevel level)
	{
		return addTag(rep, tag, level, false, false);
	}

	/**
	 *
	 * @return the file path to the content of the post
	 */
	public String getContentPath() {return contentPath;}

	/**
	 *
	 * @param contentPath
	 */
	public void setContentPath(String contentPath) {this.contentPath = contentPath;}

	/**
	 * Adds a bounded tag to this post.
	 * @param rep
	 * @param tag
	 * @param level
	 * @param bounded
	 * @param lowerbounded
	 * @return true if the tag was added, false otherwise
	 */
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

	/**
	 * Adds a default tag
	 * @param rep
	 * @param tag
	 * @return true if the tag was added, false otherwise
	 */
	public boolean addTag(AppliedSkillLevelRepository rep, Tag tag)
	{
		return addTag(rep, tag, null);
	}

	/**
	 * Removes a tag from this post
	 * @param rep
	 * @param tag
	 * @return true if the tag was removed, false otherwise
	 */
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
		owner.getPosts().remove(id);
		
		if(rep != null)
		{
			myRep.delete(this);
		}
	}

	/**
	 * Updates a given tag on this post
	 * @param rep
	 * @param tag
	 * @param level
	 * @param isbounded
	 * @param lowerbounded
	 * @return true if the tag was updated, false otherwise
	 */
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
