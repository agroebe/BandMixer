package com.application.locations;

import com.application.people.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CrudRepository<State,Long> {
    public State findByID(Long ID);
    public State findByStateName(String stateName);
}
