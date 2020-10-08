package com.application.skill_level;

import org.springframework.data.repository.CrudRepository;

import com.application.tagging.TagSkillLevelKey;

public interface AppliedSkillLevelRepository extends CrudRepository<AppliedSkillLevel, TagSkillLevelKey> 
{
	
}
