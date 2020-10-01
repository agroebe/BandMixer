package com.application.tagging;
import javax.persistence.*;

import com.application.posts.Post;

@Entity
public class AppliedSkillLevel 
{
	@EmbeddedId
	TagSkillLevelKey id;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@MapsId("post_id")
	@JoinColumn(name = "post_id")
	Post post;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@MapsId("tag_id")
	@JoinColumn(name = "tag_id")
	Tag tag;
	
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "skill_id")
	SkillLevel level;
	
	@Column(name="is_bounded", nullable=false)
	private Integer isBounded;
	
	@Column(name="is_lower_bound", nullable=false)
	private Integer isLowerBound;
	
	AppliedSkillLevel() {}
	
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
	
	public AppliedSkillLevel(Post post, Tag tag, SkillLevel level)
	{
		this(post, tag, level, false, false);
	}
	
	public AppliedSkillLevel(Post post, Tag tag)
	{
		this(post, tag, null, false, false);
	}
	
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
	
	public Boolean getIsBounded()
	{
		return (isBounded == 0? false: true);
	}
	
	public Boolean getIsLowerBound()
	{
		return (isLowerBound == 0? false: true);
	}
	
	public SkillLevel getSkillLevel()
	{
		return level;
	}
	
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
	
	public void setSkillLevel(SkillLevel level)
	{
		setSkillLevel(level, false, false);
	}
}
