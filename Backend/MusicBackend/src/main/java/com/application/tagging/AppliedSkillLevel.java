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
	
	AppliedSkillLevel() {}
	
	public AppliedSkillLevel(Post post, Tag tag, SkillLevel level)
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
	}
	
	public AppliedSkillLevel(Post post, Tag tag)
	{
		this(post, tag, null);
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
	
	public SkillLevel getSkillLevel()
	{
		return level;
	}
	
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
