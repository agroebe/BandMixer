package com.application.skill_level;
import javax.persistence.*;

import com.application.View;
import com.application.posts.Post;
import com.application.tagging.Tag;
import com.application.tagging.TagSkillLevelKey;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Entity that acts as a 3-way join table between Posts, Tags, and SkillLevels.
 * Uses Post and Tag ids as a composite key. Also indicates if a particular application of tag and skill level is a bounded 
 * SkillLevel, and if so, whether it is upper or lower bounded.
 * @author Tim Schommer
 *
 */
@Entity
public class AppliedSkillLevel 
{
	@EmbeddedId
	TagSkillLevelKey id;
	
	@JsonView({View.TagView.class,View.SkillLevelView.class})
	@ManyToOne
	@MapsId("post_id")
	@JoinColumn(name = "post_id")
	Post post;
	
	@JsonView({View.PostView.class,View.SkillLevelView.class, View.UserView.class})
	@ManyToOne
	@MapsId("tag_id")
	@JoinColumn(name = "tag_id")
	Tag tag;
	
	
	@JsonView({View.TagView.class,View.PostView.class, View.UserView.class})
	@ManyToOne
	@JoinColumn(name = "skill_id")
	SkillLevel level;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
	@Column(name="is_bounded", nullable=false)
	private Integer isBounded;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
	@Column(name="is_lower_bound", nullable=false)
	private Integer isLowerBound;
	
	/**
	 * Default no-arg constructor. There for Hibernate.
	 */
	AppliedSkillLevel() {}
	
	/**
	 * Constructs a new AppliedSkillLevel with the given Post, Tag, SkillLevel, and information on
	 * whether or not the application is bounded (i.e. SkillLevel is a bound rather than an absolute value),
	 * and if so, whether it is an upper or lower bound.
	 * @param post
	 * @param tag
	 * @param level
	 * @param isBounded
	 * @param isLowerBound
	 */
	public AppliedSkillLevel(Post post, Tag tag, SkillLevel level, boolean isBounded, boolean isLowerBound)
	{
		this.post = post;
		this.tag = tag;
		this.level = level;
		this.id = new TagSkillLevelKey(post.getId(),tag.getId());
		post.getAppliedTags().put(id, this);
		tag.getApplications().put(id, this);
		if(level != null)
		{
			level.getApplications().add(this);
		}
		
		this.isBounded = (isBounded ? 1 : 0);
		this.isLowerBound = (isBounded && isLowerBound ? 1 : 0);
	}
	
	/**
	 * Constructs an AppliedSkillLevel with the given Post, Tag, and SkillLevel.
	 * Bounded and LowerBounded default to false.
	 * @param post
	 * @param tag
	 * @param level
	 */
	public AppliedSkillLevel(Post post, Tag tag, SkillLevel level)
	{
		this(post, tag, level, false, false);
	}
	
	/**
	 * Constructs an AppliedSkillLevel with the given Post and Tag. SkillLevel defaults to null.
	 * Bounded and LowerBounded default to false.
	 * @param post
	 * @param tag
	 * @param level
	 */
	public AppliedSkillLevel(Post post, Tag tag)
	{
		this(post, tag, null, false, false);
	}
	
	/**
	 * Removes this AppliedSkillLevel from the database after unhooking itself from its relevant Tag, Post, and SkillLevel.
	 * @param rep
	 * 		The repository to be used to delete this AppliedSkillLevel.
	 */
	public void remove(AppliedSkillLevelRepository rep)
	{
		tag.getApplications().remove(id);
		post.getAppliedTags().remove(id);
		if(level != null)
		{
			level.getApplications().remove(this);
		}
		if(rep != null)
		{
			rep.delete(this);
		}
	}
	
	/**
	 * Getter for if the application is bounded.
	 * @return
	 */
	public Boolean getIsBounded()
	{
		return (isBounded == 0? false: true);
	}
	
	/**
	 * Getter for if the application is lower bounded.
	 * @return
	 */
	public Boolean getIsLowerBound()
	{
		return (isBounded == 0 || isLowerBound == 0? false: true);
	}
	
	/**
	 * Setter for if the application is bounded.
	 * @param bounded
	 */
	public void setIsBounded(boolean bounded)
	{
		isBounded = (bounded ? 1 : 0);
	}
	
	/**
	 * Setter for if the application is lower bounded.
	 * @param bounded
	 */
	public void setIsLowerBound(boolean lowerBound)
	{
		isLowerBound = (lowerBound ? 1 : 0);
	}
	
	/**
	 * Getter for the attached SkillLevel
	 * @return
	 */
	public SkillLevel getSkillLevel()
	{
		return level;
	}
	
	/**
	 * Setter for the attached SkillLevel and whether or not it is bounded/lowerBounded.
	 * @param level
	 * @param bounded
	 * @param lowerBound
	 */
	public void setSkillLevel(SkillLevel level, boolean bounded, boolean lowerBound)
	{
		if(this.level != null)
		{
			this.level.getApplications().remove(this);
		}
		
		if(level != null)
		{
			level.getApplications().add(this);
		}
		
		this.level = level;
		this.isBounded = (level != null && bounded ? 1 : 0);
		this.isLowerBound = (level != null && bounded && lowerBound ? 1 : 0);
	}
	
	/**
	 * Setter for the attached SkillLevel
	 * @param level
	 */
	public void setSkillLevel(SkillLevel level)
	{
		if(this.level != null)
		{
			this.level.getApplications().remove(this);
		}
		
		if(level != null)
		{
			level.getApplications().add(this);
		}
		
		this.level = level;
	}
}
