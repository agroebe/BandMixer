package com.tagging;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import com.posts.Post;

@Entity
public class Tag 
{
	private Long id;
	private Long tag_id;
	private Long skill_id;
	
	private Set<Post> posts = new HashSet<>();
}
