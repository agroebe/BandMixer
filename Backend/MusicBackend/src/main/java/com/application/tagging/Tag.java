package com.application.tagging;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import com.application.posts.Post;

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
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean allowskill;
	
	@OneToMany(mappedBy="tag")
	@MapKey(name="id")
	private Map<TagSkillLevelKey, AppliedSkillLevel> applications;
	
	Tag(){}
	
	public Tag(String name, boolean allowsSkill)
	{
		if(name == null)
		{
			throw new NullPointerException("Tag initialized with a null name.");
		}
		this.name = name;
		this.allowskill = allowsSkill;
	}
	
	public Tag(String name)
	{
		this(name,true);
	}
	
	public Long getId()
	{
		return this.id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Boolean allowsSkill()
	{
		return allowskill;
	}
	
	public Map<TagSkillLevelKey, AppliedSkillLevel> getApplications()
	{
		return applications;
	}
	
	public boolean setAllowsSkill(boolean allow)
	{
		allowskill = allow;
		return true;
	}
	
	public boolean setName(String newName)
	{
		if(newName != null && !newName.equals(""))
		{
			name = newName;
			return true;
		}
		return false;
	}
}
