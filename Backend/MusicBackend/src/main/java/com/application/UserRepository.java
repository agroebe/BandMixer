package com.application;

//This will be auto implemented by Spring into a bean called userRepository
//CRUD means create, read, update, delete

import com.people.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
