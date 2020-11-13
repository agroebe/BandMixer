package com.application.tagging;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface extension of a CrudRepository to be a repository for Tags.
 * Implementation provided automatically by Hibernate.
 * 
 * @author Tim Schommer
 *
 */
@Repository
public interface TagRepository extends CrudRepository<Tag,Long> 
{
	public Optional<Tag> findByName(String name);
}
