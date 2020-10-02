package com.application.tagging;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface SkillLevelRepository extends CrudRepository<SkillLevel, Long> 
{
	public Optional<SkillLevel> findByName(String name);
	
	public Optional<SkillLevel> findByValue(Integer value);
}
