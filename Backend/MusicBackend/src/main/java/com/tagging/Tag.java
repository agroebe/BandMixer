package com.tagging;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import com.posts.Post;

@Entity
public class Tag 
{
	private Long id;
	private String name;
	private Boolean allowskill;
	
	@OneToMany(mappedBy="tag")
	private Set<AppliedSkillLevel> applications;
	
}
