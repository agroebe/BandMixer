package com.application.tagging;

import java.util.ArrayList;
import java.util.Map;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonView;
import com.application.View;
import com.application.skill_level.AppliedSkillLevel;
import com.application.skill_level.AppliedSkillLevelRepository;


/**
 * Entity for a tag so that available tags can be pre-specified.
 * Has an id, name, information on whether or not looking at SkillLevels is applicable, and a set of applications it is a part of.
 * @author Tim Schommer
 *
 */
@Entity
@Table(name="TAGS")
public class Tag 
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
	@Column(name = "has_skill", nullable=false)
	private Integer allowskill;
	
	@JsonView(View.TagView.class)
	@OneToMany(mappedBy="tag")
	@MapKey(name="id")
	private Map<TagSkillLevelKey, AppliedSkillLevel> applications;
	
	/**
	 * Default constructor. Used by Hibernate.
	 */
	Tag(){}
	
	/**
	 * Constructs a Tag with the given name and the given ability to accept skill levels.
	 * @param name
	 * @param allowsSkill
	 */
	public Tag(String name, boolean allowsSkill)
	{
		if(name == null)
		{
			throw new NullPointerException("Tag initialized with a null name.");
		}
		this.name = name;
		this.allowskill = (allowsSkill ? 1 : 0);
	}
	
	/**
	 * Constructs a Tag with the given name. Accepts skill levels by default.
	 * @param name
	 */
	public Tag(String name)
	{
		this(name,true);
	}
	
	/**
	 * Getter for the id.
	 * @return
	 */
	public Long getId()
	{
		return this.id;
	}
	
	/**
	 * Getter for the name.
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Getter for whether or not it will work with SkillLevels
	 * @return
	 */
	public Boolean getAllowskill()
	{
		return (allowskill==0 ? false : true);
	}
	
	/**
	 * Getter for the map of applications.
	 * @return
	 */
	public Map<TagSkillLevelKey, AppliedSkillLevel> getApplications()
	{
		return applications;
	}
	
	/**
	 * Setter for whether or not it will work with SkillLevels
	 * @param allow
	 * @return
	 */
	public boolean setAllowsSkill(boolean allow)
	{
		allowskill = (allow ? 1 : 0);
		return true;
	}
	
	/**
	 * Setter for the name.
	 * @param newName
	 * @return
	 */
	public boolean setName(String newName)
	{
		if(newName != null && !newName.equals(""))
		{
			name = newName;
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the given Tag from the repository after unhooking and removing its relevant applications
	 * @param rep
	 * 		The repository used to delete each of the applications after unhooking them.
	 * @param myRep
	 * 		The repository used to delete this Tag after removing the relevant applications.
	 */
	public void remove(AppliedSkillLevelRepository rep, TagRepository myRep)
	{
		ArrayList<AppliedSkillLevel> vals = new ArrayList<AppliedSkillLevel>(applications.values());
		for(AppliedSkillLevel application : vals)
		{
			application.remove(rep);
		}
		if(rep != null)
		{
			myRep.delete(this);
		}
	}
}
