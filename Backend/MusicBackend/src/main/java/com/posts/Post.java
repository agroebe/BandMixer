package com.posts;
import java.util.Set;

import javax.persistence.*;
import com.tagging.*;

@Entity
@Table(name="POSTS")
public class Post 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name="owner_id")
	private Long ownerId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content_type")
	private String contentType;
	
	@Column(name="content_id", nullable=true)
	private Long contentId;
	
	@Column(name="text_content")
	private String textContent;
	
	@OneToOne
	@JoinColumn(name="content_id")
	private Content content;
	
	@OneToMany(mappedBy="post")
	private Set<AppliedSkillLevel> tags;
}
