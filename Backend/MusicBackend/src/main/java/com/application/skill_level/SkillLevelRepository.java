package com.application.skill_level;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interface extension of a CrudRepository to be a repository for SkillLevels.
 * Implementation provided automatically by Hibernate.
 * 
 * @author Tim Schommer
 *
 */
@Repository
public interface SkillLevelRepository extends CrudRepository<SkillLevel, Long> 
{
	/**
	 * Retrieves an Optional with the SkillLevel that has the given name
	 * @param name
	 * 		The name of the skill level to retrieve.
	 * @return
	 * 		An Optional wrapping around the SkillLevel with the given name (or null if no such SkillLevel exists)
	 */
	public Optional<SkillLevel> findByName(String name);
	
	/**
	 * Retrieves an Optional with the SkillLevel that has the given value
	 * @param value
	 * 		The value of the skill level to retrieve.
	 * @return
	 * 		An Optional wrapping around the SkillLevel with the given value (or null if no such SkillLevel exists)
	 */
	public Optional<SkillLevel> findByValue(Integer value);
	
	/**
	 * Retrieves all SkillLevels with a valu greater than or equal to the given value
	 * @param value
	 * @return
	 */
	@Query(value= "select * from skill_levels as lv where lv.value >= :value order by lv.value asc", nativeQuery=true)
	public List<SkillLevel> findWithGreaterOrEqual(@Param("value")Integer value);
	
	/**
	 * Retrieves an Optional with the smallest valued SkillLevel that has a value greater than the given value.
	 * @param value
	 * @return
	 */
	@Query(value="select * from skill_levels as lv where lv.value > :value order by lv.value asc limit 1", nativeQuery=true)
	public Optional<SkillLevel> findFirstGreater(@Param("value")Integer value);
	
	/**
	 * Retrieves an Optional with the largest valued SkillLevel that has a value less than the given value.
	 * @param value
	 * @return
	 */
	@Query(value="select * from skill_levels as lv where lv.value < :value order by lv.value desc limit 1", nativeQuery=true)
	public Optional<SkillLevel> findFirstLess(@Param("value")Integer value);
	
	/**
	 * Retrieves an Optional with the larget valued SkillLevel in the database.
	 * @param value
	 * @return
	 */
	@Query(value="select lv.value from skill_levels as lv order by lv.value desc limit 1", nativeQuery=true)
	public Optional<Integer> findMaxValue();
	
	/**
	 * Returns all SkillLevels with a value that is between the given lower and upper bound,
	 * upper bound is inclusive, lower bound is exclusive.
	 * @param lower
	 * @param upper
	 * @return
	 */
	@Query(value="select * from skill_levels as lv where lv.value > :lower and lv.value <= :upper order by lv.value asc", nativeQuery=true)
	public List<SkillLevel> findInIntervalUpperInclusive(@Param("lower")Integer lower, @Param("upper")Integer upper);
	
	/**
	 * Returns all SkillLevels with a value that is between the given lower and upper bound,
	 * upper bound is exclusive, lower bound is inclusive.
	 * @param lower
	 * @param upper
	 * @return
	 */
	@Query(value="select * from skill_levels as lv where lv.value >= :lower and lv.value < :upper order by lv.value desc", nativeQuery=true)
	public List<SkillLevel> findInIntervalLowerInclusive(@Param("lower")Integer lower, @Param("upper")Integer upper);
}
