package com.tagging;
import javax.persistence.Entity;

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
	
	@Column(name = "skill_id")
	private Long skillId;
	
	@ManyToOne
	@JoinColumn(name = "skill_id")
	SkillLevel level;
}
