package com.application.people;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<Profile,Long> {
    public Profile findByUsername(String username);
    public Profile findByOwner(User owner);
}
