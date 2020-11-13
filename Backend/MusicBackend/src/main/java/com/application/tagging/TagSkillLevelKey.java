package com.application.tagging;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * An embeddable key formed from the ids of a Post and a Tag.
 * Used as composite key for the AppliedSkillLevel entity.
 * @author Tim Schommer
 *
 */
@Embeddable
public class TagSkillLevelKey implements Serializable
{
	@Column(name = "post_id")
	private Long postId;
	
	@Column(name = "tag_id")
	private Long tagId;
	
	/**
	 * Default noarg constructor
	 */
	TagSkillLevelKey() {}
	
	/**
	 * Constructor that takes a Post id and a Tag id.
	 * @param postId
	 * @param tagId
	 */
	public TagSkillLevelKey(Long postId, Long tagId)
	{
		this.postId = postId;
		this.tagId = tagId;
	}
	
	/**
	 * Getter for the Post id
	 * @return
	 */
	public Long getPostId()
	{
		return postId;
	}
	
	/**
	 * Getter for the Tag id
	 * @return
	 */
	public Long getTagId()
	{
		return tagId;
	}
	
	/**
	 * Two TagSkillLevelKeys are equal if they have the same Post id and the same Tag id
	 */
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
	
	/**
	 * HashCode is the combined hash of the post and tag ids.
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(this.getPostId(), this.getTagId());
	}
}
