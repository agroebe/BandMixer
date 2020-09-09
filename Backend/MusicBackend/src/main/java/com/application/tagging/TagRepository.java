package com.application.tagging;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TagRepository extends CrudRepository<Tag,Integer> 
{
	public Optional<Tag> findByName(String name);
}
