package com.tagging;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.posts.Post;

@Entity
@Table(name="TAGS")
public class Tag 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name",unique=true, nullable=false)
	private String name;
	
	@Column(name = "has_skill", nullable=false)
	private Boolean allowskill;
	
	@OneToMany(mappedBy="tag")
	private Set<AppliedSkillLevel> applications;
	
	public Long getId()
	{
		return this.id;
	}
	
}
