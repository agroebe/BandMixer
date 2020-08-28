package com.tagging;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class SkillLevel 
{
	private Long id;
	private String name;
	private Integer value;
	
	@OneToMany(mappedBy="level")
	private Set<AppliedSkillLevel> applications;
}
