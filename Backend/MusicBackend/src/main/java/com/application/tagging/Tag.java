package com.application.tagging;

import java.util.ArrayList;
import java.util.Map;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonView;
import com.application.View;
import com.application.skill_level.AppliedSkillLevel;
import com.application.skill_level.AppliedSkillLevelRepository;


/**
 * 
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
	
	Tag(){}
	
	public Tag(String name, boolean allowsSkill)
	{
		if(name == null)
		{
			throw new NullPointerException("Tag initialized with a null name.");
		}
		this.name = name;
		this.allowskill = (allowsSkill ? 1 : 0);
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
	
	public Boolean getAllowskill()
	{
		return (allowskill==0 ? false : true);
	}
	
	public Map<TagSkillLevelKey, AppliedSkillLevel> getApplications()
	{
		return applications;
	}
	
	public boolean setAllowsSkill(boolean allow)
	{
		allowskill = (allow ? 1 : 0);
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
