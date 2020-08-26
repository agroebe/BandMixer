package com.posts;
import javax.persistence.Entity;

@Entity
public class Post 
{
	private Long id;
	private Long ownerId;
	private String title;
	private String contentType;
	private Long contentId;
	private String textContent;
}
