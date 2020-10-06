package com.application.tagging;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillLevelRepository extends CrudRepository<SkillLevel, Long> 
{
	public Optional<SkillLevel> findByName(String name);
	
	public Optional<SkillLevel> findByValue(Integer value);
	
	@Query(value= "select * from skill_levels as lv where lv.value >= :value order by lv.value asc", nativeQuery=true)
	public List<SkillLevel> findWithGreaterOrEqual(@Param("value")Integer value);
	
	@Query(value="select * from skill_levels as lv where lv.value > :value order by lv.value asc limit 1", nativeQuery=true)
	public Optional<SkillLevel> findFirstGreater(@Param("value")Integer value);
	
	@Query(value="select * from skill_levels as lv where lv.value < :value order by lv.value asc limit 1", nativeQuery=true)
	public Optional<SkillLevel> findFirstLess(@Param("value")Integer value);
	
	@Query(value="select lv.value from skill_levels as lv order by lv.value desc limit 1", nativeQuery=true)
	public Optional<Integer> findMaxValue();
	
	@Query(value="select * from skill_levels as lv where lv.value > :lower and lv.value <= :upper order by lv.value asc", nativeQuery=true)
	public List<SkillLevel> findInIntervalUpperInclusive(@Param("lower")Integer lower, @Param("upper")Integer upper);
	
	@Query(value="select * from skill_levels as lv where lv.value >= :lower and lv.value < :upper order by lv.value asc", nativeQuery=true)
	public List<SkillLevel> findInIntervalLowerInclusive(@Param("lower")Integer lower, @Param("upper")Integer upper);
}
