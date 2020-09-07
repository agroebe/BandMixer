package com.tagging;
import javax.persistence.*;

import com.posts.Post;

@Entity
public class AppliedSkillLevel 
{
	@EmbeddedId
	TagSkillLevelKey id;
	
	@ManyToOne
	@MapsId("post_id")
	@JoinColumn(name = "post_id")
	Post post;
	
	@ManyToOne
	@MapsId("tag_id")
	@JoinColumn(name = "tag_id")
	Tag tag;
	
	
	@ManyToOne
	@JoinColumn(name = "skill_id")
	SkillLevel level;
	
	public AppliedSkillLevel(Long postId, Long tagID, SkillLevel level)
	{
		id = new TagSkillLevelKey(postId, tagID);
		this.level = level;
	}
	
	public AppliedSkillLevel(Long postId, Long tagId)
	{
		this(postId, tagId, null);
	}
	
	public SkillLevel getSkillLevel()
	{
		
	}
	
	public void setSkillLevel(SkillLevel level)
	{
		
	}
}
