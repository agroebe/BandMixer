package com.application.skill_level;
import org.hibernate.annotations.Check;

import com.application.View;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
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
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class})
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class})
	@Column(name = "name",unique=true, nullable=false)
	private String name;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class})
	@Column(name = "value",unique=true, nullable=false)
	private Integer value;
	
	@JsonView(View.SkillLevelView.class)
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
		ArrayList<AppliedSkillLevel> vals = new ArrayList<AppliedSkillLevel>(applications);
		for(AppliedSkillLevel application : vals)
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
		ArrayList<AppliedSkillLevel> vals = new ArrayList<AppliedSkillLevel>(applications);
		for(AppliedSkillLevel application : vals)
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
		ArrayList<AppliedSkillLevel> vals = new ArrayList<AppliedSkillLevel>(applications);
		for(AppliedSkillLevel application : vals)
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
