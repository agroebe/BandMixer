package com.application.tagging;
import org.hibernate.annotations.Check;

import java.util.Set;

import javax.persistence.*;

/**
 * Entity for denoting valid skill levels
 * @author Tim Schommer
 *
 */
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
	
	SkillLevel() {}
	
	public SkillLevel(String levelName, int levelValue)
	{
		if(levelName == null)
		{
			throw new NullPointerException("Skill level initialized with a null name.");
		}
		else if(levelValue < 0)
		{
			throw new IllegalArgumentException("Skill level initialized with a negative value");
		}
		name = levelName;
		value = levelValue;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Integer getValue()
	{
		return value;
	}
	
	public boolean setName(String name)
	{
		if(name != null && ! name.equals(""))
		{
			this.name = name;
			return true;
		}
		return false;
	}
	
	public boolean setValue(Integer value)
	{
		if(value != null && value >= 0)
		{
			this.value = value;
			return true;
		}
		return false;
	}
	
	public Set<AppliedSkillLevel> getApplications()
	{
		return applications;
	}
	
	public void remove(AppliedSkillLevelRepository rep, SkillLevelRepository myRep)
	{
		for(AppliedSkillLevel application : applications)
		{
			application.remove(rep);
		}
		if(rep != null)
		{
			myRep.delete(this);
		}
	}
	
	public void remove(AppliedSkillLevelRepository rep, SkillLevelRepository myRep, SkillLevel refactor)
	{
		for(AppliedSkillLevel application : applications)
		{
			application.setSkillLevel(refactor);
			refactor.getApplications().add(application);
		}
		myRep.save(refactor);
		if(rep != null)
		{
			myRep.delete(this);
		}
	}
	
	public void remove(AppliedSkillLevelRepository rep, SkillLevelRepository myRep, SkillLevel refactorUp, SkillLevel refactorDown)
	{
		for(AppliedSkillLevel application : applications)
		{
			if(application.getIsLowerBound())
			{
				application.setSkillLevel(refactorUp);
				refactorUp.getApplications().add(application);
			}
			else
			{
				application.setSkillLevel(refactorDown);
				refactorDown.getApplications().add(application);
			}
		}
		myRep.save(refactorUp);
		myRep.save(refactorDown);
		if(rep != null)
		{
			myRep.delete(this);
		}
	}
}
