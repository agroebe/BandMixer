package com.application.skill_level;
import javax.persistence.*;

import com.application.View;
import com.application.posts.Post;
import com.application.tagging.Tag;
import com.application.tagging.TagSkillLevelKey;
import com.fasterxml.jackson.annotation.JsonView;

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
	
	@JsonView({View.PostView.class,View.SkillLevelView.class})
	@ManyToOne
	@MapsId("tag_id")
	@JoinColumn(name = "tag_id")
	Tag tag;
	
	
	@JsonView({View.TagView.class,View.PostView.class})
	@ManyToOne
	@JoinColumn(name = "skill_id")
	SkillLevel level;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class})
	@Column(name="is_bounded", nullable=false)
	private Integer isBounded;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class})
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
		return (isBounded == 0 || isLowerBound == 0? false: true);
	}
	
	public void setIsBounded(boolean bounded)
	{
		isBounded = (bounded ? 1 : 0);
	}
	
	public void setIsLowerBound(boolean lowerBound)
	{
		isLowerBound = (lowerBound ? 1 : 0);
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
