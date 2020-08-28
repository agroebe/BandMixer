package com.tagging;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TagSkillLevelKey implements Serializable
{
	@Column(name = "post_id")
	private Long postId;
	
	@Column(name = "tag_id")
	private Long tagId;
	
	public TagSkillLevelKey() {}
	
	public TagSkillLevelKey(Long postId, Long tagId)
	{
		this.postId = postId;
		this.tagId = tagId;
	}
	
	public Long getPostId()
	{
		return postId;
	}
	
	public Long getTagId()
	{
		return tagId;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == null || !(o instanceof TagSkillLevelKey))
		{
			return false;
		}
		TagSkillLevelKey other = (TagSkillLevelKey) o;
		return Objects.equals(this.getPostId(), other.getPostId()) && Objects.equals(this.getTagId(), other.getTagId());
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(this.getPostId(), this.getTagId());
	}
}
