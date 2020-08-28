package com.posts;
import javax.persistence.Entity;
import com.tagging.*;

@Entity
public class Post 
{
	private Long id;
	private Long ownerId;
	private String title;
	private String contentType;
	private Long contentId;
	private String textContent;
	
	@OneToMany(mappedBy="post")
	private Set<AppliedSkillLevel> tags;
}
