package com.tagging;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="SKILL_LEVELS")
@Check(constraints="value >= 0")
public class SkillLevel 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name",unique=true, nullable=false)
	private String name;
	
	@Column(name = "value",unique=true, nullable=false)
	private Integer value;
	
	@OneToMany(mappedBy="level")
	private Set<AppliedSkillLevel> applications;
}
