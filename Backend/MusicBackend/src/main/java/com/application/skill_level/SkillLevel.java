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
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
	@Column(name = "name",unique=true, nullable=false)
	private String name;
	
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
	@Column(name = "value",unique=true, nullable=false)
	private Integer value;
	
	@JsonView(View.SkillLevelView.class)
	@OneToMany(mappedBy="level")
	private Set<AppliedSkillLevel> applications;
	
	/**
	 * Basic, no arg constructor.
	 */
	SkillLevel() {}
	
	/**
	 * Constructor that takes a name and value for the SkillLevel
	 * @param levelName
	 * 		The name of the skill level
	 * @param levelValue
	 * 		The numerical value of the skill level
	 */
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
	
	/**
	 * Getter for the id.
	 * @return
	 * 		Returns the id of the SkillLevel
	 */
	public Long getId()
	{
		return id;
	}
	
	/**
	 * Getter for the name.
	 * @return
	 * 		Returns the name of the SkillLevel
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Getter for the value.
	 * @return
	 * 		Returns the value of the SkillLevel
	 */
	public Integer getValue()
	{
		return value;
	}
	
	/**
	 * Setter for the name of the SkillLevel.
	 * @param name
	 * @return
	 * 		Returns true if the value passed in for name was valid and false otherwise.
	 */
	public boolean setName(String name)
	{
		if(name != null && ! name.equals(""))
		{
			this.name = name;
			return true;
		}
		return false;
	}
	
	/**
	 * Setter for the name of the SkillLevel.
	 * @param name
	 * @return
	 * 		Returns true if the value passed in for name was valid and false otherwise.
	 */
	public boolean setValue(Integer value)
	{
		if(value != null && value >= 0)
		{
			this.value = value;
			return true;
		}
		return false;
	}
	
	/**
	 * Getter for the set of AppliedSkillLevels in which this skill level is applied.
	 * @return
	 * 		Returns the set of AppliedSkillLevels in which this skill level is applied
	 */
	public Set<AppliedSkillLevel> getApplications()
	{
		return applications;
	}
	
	/**
	 * Removes the given SkillLevel and does not refactor applications that contain it.
	 * @param rep
	 * 		The AppliedSkillLevelRepository so that the applications can delete themselves after unhooking from the other entries.
	 * @param myRep
	 * 		The SkillLevelRepository so that this SkillLevel can delete itself.
	 */
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
	
	/**
	 * Removes the given SkillLevel and refactors all applications that contain it to one
	 * specific SkillLevel.
	 * @param rep
	 *  	The AppliedSkillLevelRepository so that the applications can save themselves after updating the skill level they are hooked up to.
	 * @param myRep
	 * 		The SkillLevelRepository so that this SkillLevel can delete itself.
	 * @param refactor
	 * 		The SkillLevel to be used to replace this one in all applications
	 */
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
	
	/**
	 * Removes the given SkillLevel and refactors all applications that contain it either upwards or downwards.
	 * @param rep
	 *  	The AppliedSkillLevelRepository so that the applications can save themselves after updating the skill level they are hooked up to.
	 * @param myRep
	 * 		The SkillLevelRepository so that this SkillLevel can delete itself.
	 * @param refactorUp
	 * 		The SkillLevel to be used to replace this one in applications that are refactored up to a new SkillLevel
	 * @param refactorDown
	 * 	The SkillLevel to be used to replace this one in applications that are refactored down to a new SkillLevel
	 */
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
