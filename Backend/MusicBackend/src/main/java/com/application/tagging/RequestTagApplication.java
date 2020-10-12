package com.application.tagging;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;

import com.application.skill_level.RequestSkillLevel;

import validation.annotations.NullChecks;
import validation.ordergroups.First;
import validation.ordergroups.Second;

@GroupSequence({First.class, Second.class})
@NullChecks(groups= {First.class}, fields= {"tag","skill"})
public class RequestTagApplication 
{
	@Valid
	@ConvertGroup(to=Second.class)
	private RequestExistentTag tag;
	
	@Valid
	@ConvertGroup(to=Second.class)
	private RequestSkillLevel skill;
	
	private boolean bounded;
	
	private boolean lowerBounded;
	
	public RequestTagApplication()
	{
		tag = null;
		skill = new RequestSkillLevel();
		bounded = false;
		lowerBounded = false;
	}
	
	public RequestExistentTag getTag()
	{
		return tag;
	}
	
	public void setTag(RequestExistentTag tag)
	{
		this.tag = tag;
	}
	
	public RequestSkillLevel getSkill()
	{
		return skill;
	}
	
	public void setSkill(RequestSkillLevel skill)
	{
		this.skill = (skill == null ? new RequestSkillLevel() : skill);
	}
	
	public boolean getBounded()
	{
		return bounded;
	}
	
	public void setBounded(boolean bounded)
	{
		this.bounded = bounded;
	}
	
	public boolean getLowerBounded()
	{
		return bounded && lowerBounded;
	}
	
	public void setLowerBounded(boolean lowerBounded)
	{
		this.lowerBounded = lowerBounded;
	}
}
