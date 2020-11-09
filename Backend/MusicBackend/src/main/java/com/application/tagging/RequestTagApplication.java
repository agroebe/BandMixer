package com.application.tagging;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;

import com.application.skill_level.RequestSkillLevel;

import validation.annotations.NullChecks;
import validation.ordergroups.First;
import validation.ordergroups.Second;

/**
 * Wrapper class on the information needed to specify a pairing of Tag and SkillLevel.
 * Contains data on an existent Tag, an existent SkillLevel, and bounding information for the application.
 * @author Tim Schommer
 *
 */
@GroupSequence({ First.class,RequestTagApplication.class, Second.class})
@NullChecks(groups= {First.class}, fields= {"tag","skill"})
public class RequestTagApplication 
{
	@Valid
	private RequestExistentTag tag;
	
	@Valid
	private RequestSkillLevel skill;
	
	private boolean bounded;
	
	private boolean lowerBounded;
	
	/**
	 * Default constructor. No Tag, the default SkillLevel ("unset"), and no bounding or lower bounding.
	 */
	public RequestTagApplication()
	{
		tag = null;
		skill = new RequestSkillLevel();
		bounded = false;
		lowerBounded = false;
	}
	
	/**
	 * Getter for the Tag information.
	 * @return
	 */
	public RequestExistentTag getTag()
	{
		return tag;
	}
	
	/**
	 * Setter for the Tag information.
	 * @param tag
	 */
	public void setTag(RequestExistentTag tag)
	{
		this.tag = tag;
	}
	
	/**
	 * Getter for the SkillLevel information.
	 * @return
	 */
	public RequestSkillLevel getSkill()
	{
		return skill;
	}
	
	/**
	 * Setter for the SkillLevel information.
	 * @param skill
	 */
	public void setSkill(RequestSkillLevel skill)
	{
		this.skill = (skill == null ? new RequestSkillLevel() : skill);
	}
	
	/**
	 * Getter for the isBounded information.
	 * @return
	 */
	public boolean getBounded()
	{
		return bounded;
	}
	
	/**
	 * Setter for the isBounded information.
	 * @param bounded
	 */
	public void setBounded(boolean bounded)
	{
		this.bounded = bounded;
	}
	
	/**
	 * Getter for the isLowerBounded information.
	 * @return
	 */
	public boolean getLowerBounded()
	{
		return bounded && lowerBounded;
	}
	
	/**
	 * Setter for the isLowerBounded information.
	 * @param lowerBounded
	 */
	public void setLowerBounded(boolean lowerBounded)
	{
		this.lowerBounded = lowerBounded;
	}
}
