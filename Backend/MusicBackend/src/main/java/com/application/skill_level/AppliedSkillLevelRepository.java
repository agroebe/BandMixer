package com.application.skill_level;

import org.springframework.data.repository.CrudRepository;

import com.application.tagging.TagSkillLevelKey;

/**
 * Interface extension of a CrudRepository to be a repository for AppliedSkillLevels.
 * Implementation provided automatically by Hibernate.
 * 
 * @author Tim Schommer
 *
 */
public interface AppliedSkillLevelRepository extends CrudRepository<AppliedSkillLevel, TagSkillLevelKey> 
{
	
}
